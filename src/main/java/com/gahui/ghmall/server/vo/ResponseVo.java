package com.gahui.ghmall.server.vo;

import com.gahui.ghmall.server.constant.ResponseEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: controller层响应返回
 * @author: Gahui
 * @since: 2021/2/4
 **/
@Getter
@Setter
public class ResponseVo<T> {

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;


    public ResponseVo(int code) {
        this.code = code;
    }

    public ResponseVo(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseVo(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseVo(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    public ResponseVo(ResponseEnum responseEnum, T t) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.data = t;
    }

    /**
     * 响应成功
     */
    public static ResponseVo success() {
        return new ResponseVo(ResponseEnum.SUCCESS);
    }

    public static <T> ResponseVo success(T t) {
        ResponseVo responseVo = new ResponseVo(ResponseEnum.SUCCESS);
        responseVo.data = t;
        return responseVo;
    }

    /**
     * 响应失败
     */
    public static ResponseVo fail() {
        return new ResponseVo(ResponseEnum.FAIL);
    }

    public static <T> ResponseVo fail(T t) {
        ResponseVo responseVo = new ResponseVo(ResponseEnum.FAIL);
        responseVo.data = t;
        return responseVo;
    }

    /**
     * 权限校验失败
     */
    public static ResponseVo auth() {
        return new ResponseVo(ResponseEnum.AUTH);
    }

    public static <T> ResponseVo auth(T t) {
        ResponseVo responseVo = new ResponseVo(ResponseEnum.AUTH);
        responseVo.data = t;
        return responseVo;
    }

    /**
     * 出现未知错误
     */
    public static ResponseVo unknow() {
        return new ResponseVo(ResponseEnum.UNKNOW);
    }
}
