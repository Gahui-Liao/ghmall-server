package com.gahui.ghmall.server.service.account;

import com.gahui.ghmall.server.dto.GhAccountDto;

/**
 * @description: 账户相关业务接口
 * @author: Gahui
 * @since: 2021/3/16
 **/
public interface AccountService {

    /**
     * 登录
     *
     * @param accountName 账户名
     * @param password    密码
     * @return token
     */
    String login(String accountName, String password);

    /**
     * 注册
     *
     * @param accountDto
     * @return
     */
    int register(GhAccountDto accountDto);
}
