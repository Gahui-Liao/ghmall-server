package com.gahui.ghmall.server.cache;

/**
 * @description: 账户类缓存服务
 * @author: Gahui
 * @since: 2021/3/16
 **/
public interface AccountCacheService {

    /**
     * 用于访问业务逻辑的缓存名称
     * 需在ehcaceh.xml中对缓存进行配置
     */
    String ACCOUNT_CACHE = "a_cache";

    /**
     * 获取AccountId
     *
     * @return accountId
     */
    Integer getAccountId();
}
