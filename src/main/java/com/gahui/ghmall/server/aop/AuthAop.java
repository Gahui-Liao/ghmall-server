package com.gahui.ghmall.server.aop;

import com.gahui.ghmall.server.constant.ExceptionEnum;
import com.gahui.ghmall.server.exception.GhmallException;
import com.gahui.ghmall.server.service.token.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;

/**
 * @description: 权限控制AOP
 * @author: Gahui
 * @since: 2021/3/18
 **/
@Slf4j
@Aspect
@Component
public class AuthAop {

    @Resource
    TokenService tokenService;

    @Pointcut("@annotation(com.gahui.ghmall.server.annotation.GhAuth)")
    public void aExecution() {
    }

    @Before("aExecution()")
    public void beforeAExecution() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        if (null != sra) {
            String ghToken = sra.getRequest().getHeader("GH-token");
            if (ghToken == null || tokenService.decode(ghToken) == null) {
                throw new GhmallException(ExceptionEnum.AUTH.getCode(), "请先登录！");
            }
        }
    }
}
