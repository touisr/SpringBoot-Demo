package com.laika.demoservice.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author: 会跳舞的机器人
 * @date: 17/12/19 下午3:44
 * @description: 系统编码生成工具类
 */
public class CodeUtils {

    /**
     * 生成客户编号(代理商编号+7位随机数)
     *
     * @param agentCode
     * @return
     */
    public static String getCustomerCode(String agentCode) {
        StringBuffer sb = new StringBuffer();
        sb.append(agentCode);
        sb.append(RandomStringUtils.randomNumeric(7));
        return sb.toString();
    }

    /**
     * 生成6位数的短信验证码
     *
     * @return
     */
    public static String getSmsCode() {

        return RandomStringUtils.randomNumeric(6);
    }
}
