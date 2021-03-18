package com.gahui.ghmall.server.aop;

import com.gahui.ghmall.server.constant.ExceptionEnum;
import com.gahui.ghmall.server.exception.GhmallException;
import com.gahui.ghmall.server.vo.ResponseVo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description: 全局异常捕获
 * @author: Gahui
 * @since: 2021/3/18
 **/
@RestControllerAdvice
public class GhGlobalExceptionAop {

    @ExceptionHandler(value = GhmallException.class)
    public ResponseVo ghmallExceptionHandler(GhmallException ge) {
        if (ge.getExcCode().equals(ExceptionEnum.AUTH.getCode())) {
            return ResponseVo.auth(ge.getMessage());
        }
        return ResponseVo.fail(ge.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseVo exceptionHandler(Exception e) {
        return ResponseVo.fail(new GhmallException(e).getMessage());
    }
}
