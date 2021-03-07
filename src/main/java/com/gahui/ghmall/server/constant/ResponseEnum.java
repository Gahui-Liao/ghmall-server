package com.gahui.ghmall.server.constant;

/**
 * @description: 响应枚举类
 * @author: Gahui
 * @since: 2021/2/4
 **/
public enum ResponseEnum {
    /**
     * 响应成功
     */
    SUCCESS(1, "success"),
    /**
     * 响应失败
     */
    FAIL(-1, "fail"),
    /**
     * 权限校验失败
     */
    AUTH(233,"forbidden"),
    /**
     * 未知
     */
    UNKNOW(9527, "unknow");

    /**
     * 响应码
     */
    private int code;
    /**
     * 响应信息
     */
    private String message;

    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
