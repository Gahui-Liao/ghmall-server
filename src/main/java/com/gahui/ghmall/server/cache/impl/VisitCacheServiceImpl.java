package com.gahui.ghmall.server.cache.impl;

import com.gahui.ghmall.server.cache.VisitCacheService;
import com.gahui.ghmall.server.util.GhCacheHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 访问相关缓存业务逻辑
 * @author: Gahui
 * @since: 2021/2/5
 **/
@Service("visitCacheService")
public class VisitCacheServiceImpl implements VisitCacheService {

    @Resource
    GhCacheHelper ghCacheHelper;

    @Override
    public int putVisit(String ip) {
        return ghCacheHelper.incr(V_CACHE, ip);
    }
}
