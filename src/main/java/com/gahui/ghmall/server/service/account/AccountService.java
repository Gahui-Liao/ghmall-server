package com.gahui.ghmall.server.service.account;

/**
 * @description: 账户相关业务接口
 * @author: Gahui
 * @since: 2021/3/16
 **/
public interface AccountService {

    /**
     * 登录
     * @param accountName 账户名
     * @param password 密码
     * @return token
     */
    String login(String accountName, String password);
}
