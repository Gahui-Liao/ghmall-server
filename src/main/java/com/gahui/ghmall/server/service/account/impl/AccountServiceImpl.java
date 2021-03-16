package com.gahui.ghmall.server.service.account.impl;

import com.gahui.ghmall.server.constant.ExceptionEnum;
import com.gahui.ghmall.server.dao.GhAccountDao;
import com.gahui.ghmall.server.dto.GhAccountDto;
import com.gahui.ghmall.server.exception.GhmallException;
import com.gahui.ghmall.server.service.account.AccountService;
import com.gahui.ghmall.server.service.token.TokenService;
import com.gahui.ghmall.server.util.AesHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 账户业务逻辑具体实现类
 * @author: Gahui
 * @since: 2021/3/16
 **/
@Slf4j
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Value("${aes.key}")
    private String aesKey;

    @Resource
    GhAccountDao ghAccountDao;

    @Resource
    TokenService tokenService;

    @Override
    public String login(String accountName, String password) {
        GhAccountDto accountDto = ghAccountDao.getAccountByName(accountName);
        if (accountDto == null) {
            throw new GhmallException(ExceptionEnum.BIZ.getCode(), "用户不存在！");
        }
        try {
            if (AesHelper.encrypt(password.trim(), aesKey).equals(accountDto.getAccountPassword())) {
                return tokenService.encode(accountDto.getAccountId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("用户密码加密错误，accountName ===> {}，password ===> {}", accountName, password);
            throw new GhmallException(ExceptionEnum.BIZ.getCode(), "密码加密错误！");
        }
        return null;
    }
}
