package com.gahui.ghmall.server.cache;

/**
 * @description: 访问相关缓存服务
 * @author: Gahui
 * @since: 2021/2/5
 **/
public interface PvCacheService {

    /**
     * 用于访问业务逻辑的缓存名称
     * 需在ehcaceh.xml中对缓存进行配置
     */
    String PV_CACHE = "pv_cache";

    /**
     * 更新缓存中的访问量
     * @return 1-成功；0-失败
     */
    int updatePvCount() throws Exception;
}
