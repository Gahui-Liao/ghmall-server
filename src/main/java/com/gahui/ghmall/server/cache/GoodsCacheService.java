package com.gahui.ghmall.server.cache;

/**
 * @description: 商品类缓存服务
 * @author: Gahui
 * @since: 2021/2/4
 **/
public interface GoodsCacheService {

    /**
     * 用于商品业务逻辑的缓存名称
     * 需在ehcaceh.xml中对缓存进行配置
     */
    String GOODS_CACHE = "goods_cache";


}
