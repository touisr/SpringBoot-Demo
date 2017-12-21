package com.laika.demoservice.configurer;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.laika.demoservice.annotation.UnAuthorization;
import com.laika.demoservice.core.ServiceException;
import com.laika.demoservice.core.api.ApiResult;
import com.laika.demoservice.core.api.ApiResultCodeMsg;
import com.laika.demoservice.utils.WebUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: 会跳舞的机器人
 * @date: 2017/7/7 09:22
 * @description: Spring MVC 配置
 */
@Configuration
@Slf4j
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    //使用阿里 FastJson 作为JSON MessageConverter
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter4 converter = new FastJsonHttpMessageConverter4();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue,//保留空的字段
                SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
                SerializerFeature.WriteNullNumberAsZero,//Number null -> 0
                SerializerFeature.WriteDateUseDateFormat); //Date ->yyyy-MM-dd HH:mm:ss
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(converter);
    }


    //统一异常处理
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            @Override
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
                ApiResult result = null;
                if (e instanceof ServiceException) {//业务失败的异常，如“账号或密码错误”
                    result = new ApiResult(ApiResultCodeMsg.FAIL.getCode(), e.getMessage());
                    log.info(e.getMessage());
                } else if (e instanceof NoHandlerFoundException) {
                    result = new ApiResult(ApiResultCodeMsg.NOT_FOUND.getCode(), "接口 [" + request.getRequestURI() + "] 不存在");
                } else if (e instanceof ServletException) {
                    result = new ApiResult(ApiResultCodeMsg.FAIL.getCode(), e.getMessage());
                } else if (e instanceof BindException) { // @Valid参数校验异常
                    BindException bindException = (BindException) e;
                    List<ObjectError> errors = bindException.getBindingResult().getAllErrors();
                    if (!CollectionUtils.isEmpty(errors)) {
                        StringBuffer sb = new StringBuffer();
                        for (ObjectError error : errors) {
                            sb.append(error.getDefaultMessage());
                            sb.append("\n");
                        }
                        log.info(sb.toString());
                        // 取第一条校验失败的提示信息吧
                        ObjectError objectError = errors.get(0);
                        result = new ApiResult(ApiResultCodeMsg.BAD_PARAMETER.getCode(), objectError.getDefaultMessage());
                    }
                } else {
                    result = new ApiResult(ApiResultCodeMsg.INTERNAL_SERVER_ERROR.getCode(), "接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                    String message;
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                e.getMessage());
                    } else {
                        message = e.getMessage();
                    }
                    log.error(message, e);
                }
                responseResult(response, result);
                return new ModelAndView();
            }

        });
    }

    //解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                // 请求方法没有添加UnAuthorization注解，则需要token鉴权
                if (handlerMethod.getMethodAnnotation(UnAuthorization.class) == null) {
                    boolean flag = validateToken(request);
                    if (flag) {
                        return true;
                    } else {
                        log.warn("鉴权失败，请求接口：{}，请求IP：{}，请求参数：{}",
                                request.getRequestURI(), WebUtils.getIpAddress(request), JSON.toJSONString(request.getParameterMap()));
                        ApiResult result = new ApiResult(ApiResultCodeMsg.UNAUTHORIZED.getCode(), ApiResultCodeMsg.UNAUTHORIZED.getMsg());
                        responseResult(response, result);
                        return false;
                    }
                }
                return true;
            }
        });
    }

    /**
     * 校验token的有效性
     */
    private boolean validateToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }
        try {
            //jwtHelper.getClaimsFromToken(token);
        } catch (Exception e) {
            log.warn("鉴权失败");
            return false;
        }
        return true;
    }

    /**
     * @param response
     * @param result
     */
    private void responseResult(HttpServletResponse response, ApiResult result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }
}
