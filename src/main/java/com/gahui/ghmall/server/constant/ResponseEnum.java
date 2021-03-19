package com.gahui.ghmall.server.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 响应枚举类
 * @author: Gahui
 * @since: 2021/2/4
 **/
@Getter
@AllArgsConstructor
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
}
