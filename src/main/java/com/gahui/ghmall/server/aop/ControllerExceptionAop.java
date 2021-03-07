package com.gahui.ghmall.server.aop;

import com.gahui.ghmall.server.constant.ExceptionEnum;
import com.gahui.ghmall.server.exception.GhmallException;
import com.gahui.ghmall.server.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @description: controller异常处理AOP，用于统一处理异常
 * @author: Gahui
 * @since: 2021/2/5
 **/
@Slf4j
@Aspect
@Component
public class ControllerExceptionAop {

    @Pointcut("execution(* com.gahui.ghmall.server.controller.*Controller.*(..))")
    public void ceExecution() {
    }

    @Around("ceExecution()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return this.handleException(joinPoint, throwable);
        }
    }

    /**
     * 异常的处理
     */
    private Object handleException(ProceedingJoinPoint joinPoint, Throwable throwable) {
        // 日志打印类名和方法名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info("controller访问异常，类名为===>{}，方法名为===>{}",
                methodSignature.getDeclaringTypeName(),
                methodSignature.getMethod().getName());
        if (throwable instanceof GhmallException) {
            // 如果是权限异常
            if (((GhmallException) throwable).getExcCode().equals(ExceptionEnum.AUTH.getCode())) {
                return ResponseVo.auth(throwable.getMessage());
            }
            return ResponseVo.fail(throwable.getMessage());
        }
        return ResponseVo.fail(new GhmallException(throwable).getMessage());
    }
}
