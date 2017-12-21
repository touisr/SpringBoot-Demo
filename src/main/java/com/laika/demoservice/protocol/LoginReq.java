package com.laika.demoservice.protocol;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author: 会跳舞的机器人
 * @date: 17/12/16 上午11:26
 * @description: 登录请求参数
 */
@Data
public class LoginReq {
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    private String password;

    private String smsCode;

    @NotBlank(message = "代理商编号不能为空")
    private String agentCode;

}
