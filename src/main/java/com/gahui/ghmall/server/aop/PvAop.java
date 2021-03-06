package com.gahui.ghmall.server.aop;

import com.gahui.ghmall.server.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 访问AOP，用于：访问量统计、访问权限控制等
 * @author: Gahui
 * @since: 2021/2/3
 **/
@Slf4j
@Aspect
@Component
public class PvAop {

    @Pointcut("execution(* com.gahui.ghmall.server.controller.*Controller.*(..))")
    public void pvExecution() {
    }

    @Before("pvExecution()")
    public void doBefore() {
        // TODO 权限控制：如登录后访问，一秒内只能访问多少次
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        if (sra != null) {
            HttpServletRequest request = sra.getRequest();
            // 获取远程客户端IP
            log.info("remote request addr ===> {}", request.getRemoteAddr());
            log.info("token ===> {}", request.getHeader("token"));
            // 权限校验，未校验通过抛出异常

        }

    }

    @After("pvExecution()")
    public void doAfter() {
        // TODO 访问量记录，首先写入缓存，定时任务扫描入库

    }
}
