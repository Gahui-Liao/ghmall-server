package com.gahui.ghmall.server.cache;

/**
 * @description: 访问相关缓存服务
 * @author: Gahui
 * @since: 2021/2/5
 **/
public interface VisitCacheService {

    /**
     * 用于访问业务逻辑的缓存名称
     * 需在ehcaceh.xml中对缓存进行配置
     */
    String V_CACHE = "v_cache";

    /**
     * 增加独立访问量
     * @param ip 访问的IP
     * @return 1-成功；0-失败
     */
    int incrVisit(String ip);

    /**
     * IP访问限制
     * @param ip 访问的IP
     * @return 1-允许；0-禁止
     */
    int accessAuth(String ip);

    /**
     * 独立访问量统计
     * @return
     */
    int uv();

}
