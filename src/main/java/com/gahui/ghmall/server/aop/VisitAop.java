package com.gahui.ghmall.server.aop;

import com.gahui.ghmall.server.cache.VisitCacheService;
import com.gahui.ghmall.server.constant.ExceptionEnum;
import com.gahui.ghmall.server.exception.GhmallException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    VisitCacheService visitCacheService;

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
            String ip = request.getRemoteAddr();
            visitCacheService.incrVisit(ip);
            if (visitCacheService.accessAuth(ip) != 1) {
                throw new GhmallException(ExceptionEnum.AUTH);
            }
        }
    }
}
