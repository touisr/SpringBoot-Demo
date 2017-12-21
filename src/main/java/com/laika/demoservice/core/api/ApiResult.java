package com.laika.demoservice.core.api;

/**
 * @author 会跳舞的机器人
 * @version 1.0
 * @Description Controller层返回的统一结果对象
 * @date 2015年8月31日 下午11:57:43
 */
public class ApiResult {

    /**
     * 状态码
     **/
    private int rc;

    /**
     * 状态码对应的提示信息
     **/
    private String msg;

    /**
     * 返回的具体数据
     **/
    private Object data;


    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ApiResult() {
    }

    public ApiResult(int rc, String msg) {
        super();
        this.rc = rc;
        this.msg = msg;
        this.data = "";
    }

    public ApiResult(int rc, String msg, Object data) {
        super();
        this.rc = rc;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "rc=" + rc +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
