package com.gahui.ghmall.server.cache.impl;

import com.gahui.ghmall.server.DatabaseJunitTest;
import com.gahui.ghmall.server.cache.AccountCacheService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @description:
 * @author: Gahui
 * @since: 2021/3/17
 **/
@SpringBootTest
class AccountCacheServiceImplTest extends DatabaseJunitTest {

    @Resource
    AccountCacheService accountCacheService;

    @Test
    void getAccountId() {
        System.out.println(accountCacheService.getAccountId());
    }
}
