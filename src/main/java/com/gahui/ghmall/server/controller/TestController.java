package com.gahui.ghmall.server.controller;

import com.gahui.ghmall.server.cache.AccountCacheService;
import com.gahui.ghmall.server.constant.ExceptionEnum;
import com.gahui.ghmall.server.exception.GhmallException;
import com.gahui.ghmall.server.service.TestService;
import com.gahui.ghmall.server.service.account.AccountService;
import com.gahui.ghmall.server.vo.ResponseVo;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 测试controller
 * @author: Gahui
 * @since: 2021/2/2
 **/
@RestController
public class TestController {
    @Resource
    AccountService accountService;
    @Resource
    AccountCacheService accountCacheService;

    @RequestMapping("/test")
    public ResponseVo test() {
            throw new NullPointerException();
//        return ResponseVo.success();
    }

    @RequestMapping("/login")
    public ResponseVo login() {
        System.out.println(accountCacheService.getAccountId());accountCacheService.getAccountId();
        return ResponseVo.success(accountService.login("",""));
    }

    @RequestMapping("/auth")
    public ResponseVo auth() {

        throw new GhmallException(ExceptionEnum.AUTH);
    }
}
