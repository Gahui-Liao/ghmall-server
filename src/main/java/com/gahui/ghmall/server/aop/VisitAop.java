package com.gahui.ghmall.server.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 访问AOP
 * @author: Gahui
 * @since: 2021/3/12
 **/
@Slf4j
@Aspect
@Component
public class VisitAop {
    @Pointcut("execution(* com.gahui.ghmall.server.controller.*Controller.*(..))")
    public void vExecution() {
    }

    @Before("vExecution()")
    public void beforeVExecution() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        if (null != sra) {
            HttpServletRequest request = sra.getRequest();
            // 获取访问的IP地址
            String vIp = request.getRemoteAddr();

        }
    }
}
