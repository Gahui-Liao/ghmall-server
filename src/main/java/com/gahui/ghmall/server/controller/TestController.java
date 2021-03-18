package com.gahui.ghmall.server.controller;

import com.gahui.ghmall.server.constant.ExceptionEnum;
import com.gahui.ghmall.server.exception.GhmallException;
import com.gahui.ghmall.server.service.account.AccountService;
import com.gahui.ghmall.server.vo.ResponseVo;
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

    @RequestMapping("/test")
    public ResponseVo test() {
        throw new NullPointerException();
//        return ResponseVo.success();
    }

    @RequestMapping("/login")
    public ResponseVo login() {
        return ResponseVo.success(accountService.login("", ""));
    }

    @RequestMapping("/auth")
    public ResponseVo auth() {

        throw new GhmallException(ExceptionEnum.AUTH);
    }
}
