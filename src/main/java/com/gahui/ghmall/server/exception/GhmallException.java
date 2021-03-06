package com.gahui.ghmall.server.exception;

import com.gahui.ghmall.server.constant.ExceptionEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: 异常封装
 * @author: Gahui
 * @since: 2021/2/5
 **/
@Getter
@Setter
public class GhmallException extends RuntimeException {
    /**
     * 异常编码
     */
    private String excCode;

    /**
     * 异常信息
     */
    private String excMsg;

    /**
     * 传入异常信息来构造异常
     */
    public GhmallException(String excMsg) {
        this.excCode = ExceptionEnum.OTH.getCode();
        this.excMsg = excMsg;
    }

    public GhmallException(String excCode, String excMsg) {
        this.excCode = excCode;
        this.excMsg = excMsg;
    }

    public GhmallException(String excCode,Throwable throwable){
        this.excCode = excCode;
        this.excMsg = throwable.getMessage() == null ? String.valueOf(throwable) : throwable.getMessage();
    }

    public GhmallException(Throwable throwable){
        this(ExceptionEnum.OTH.getCode(),throwable);
    }

    public GhmallException(ExceptionEnum exceptionEnum) {
        this.excCode = exceptionEnum.getCode();
        this.excMsg = exceptionEnum.getDesc();
    }

    @Override
    public String getMessage() {
        return this.getExcCode() + " - " + this.getExcMsg();

    }
}
