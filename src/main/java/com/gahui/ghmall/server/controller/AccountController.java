package com.gahui.ghmall.server.controller;

import com.gahui.ghmall.server.dto.GhAccountDto;
import com.gahui.ghmall.server.service.account.AccountService;
import com.gahui.ghmall.server.vo.ResponseVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description: 账户相关Controller
 * @author: Gahui
 * @since: 2021/3/17
 **/
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    AccountService accountService;

    @PostMapping("/login")
    public ResponseVo login(@RequestBody GhAccountDto ghAccountDto) {
        String token = accountService.login(ghAccountDto.getAccountName(), ghAccountDto.getAccountPassword());
        if (token != null) {
            return ResponseVo.success(token);
        }
        return ResponseVo.fail();
    }

    @GetMapping("/validate/{accountName}")
    public ResponseVo validateAccountName(@PathVariable("accountName") String accountName) {
        if (accountService.validateAccountName(accountName) == 0) {
            return ResponseVo.success();
        }
        return ResponseVo.fail();
    }


    @PostMapping("/register")
    public ResponseVo register(@RequestBody GhAccountDto ghAccountDto) {
        if (accountService.register(ghAccountDto) == 1) {
            return ResponseVo.success();
        }
        return ResponseVo.fail();
    }
}
