
package com.laika.demoservice.core.api;

/**
 * @author 会跳舞的机器人
 * @version 1.0
 * @Description Controller层返回的统一结果对象信息码
 * @date 2015年8月31日 下午11:58:29
 */
public enum ApiResultCodeMsg {
    SUCCESS(1, "成功"),//成功
    FAIL(-1, "失败"),//失败
    USER_NOT_FOUND(-100,"用户名或者密码错误"),
    UNAUTHORIZED(-401, "鉴权错误"),//未认证（签名错误）
    NOT_FOUND(-404, "接口不存在"),//接口不存在
    BAD_PARAMETER(-205, "参数错误"),
    INTERNAL_SERVER_ERROR(-500, "服务器内部错误");//服务器内部错误

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ApiResultCodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据状态码获取到对应的提示信息
     *
     * @param code
     * @return
     */
    public static String getMsg(int code) {
        for (ApiResultCodeMsg resultCodeMsg : values()) {
            if (resultCodeMsg.getCode() == code) {
                return resultCodeMsg.getMsg();
            }
        }
        return null;
    }

}
