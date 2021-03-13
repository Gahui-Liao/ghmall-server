package com.gahui.ghmall.server.cache.impl;

import com.gahui.ghmall.server.cache.VisitCacheService;
import com.gahui.ghmall.server.util.GhCacheHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 访问相关缓存业务逻辑
 * @author: Gahui
 * @since: 2021/2/5
 **/
@Slf4j
@Service("visitCacheService")
public class VisitCacheServiceImpl implements VisitCacheService {

    private final String UV_PREFIX = "uv#";

    private final String AC_PREFIX = "ac#";

    @Resource
    GhCacheHelper ghCacheHelper;

    @Value("${visit.vps:10}")
    private int vps;

    @Override
    public int incrVisit(String ip) {
        return ghCacheHelper.incr(V_CACHE, UV_PREFIX + ip);
    }

    @Override
    public int accessAuth(String ip) {
        String key = AC_PREFIX + ip;
        if (ghCacheHelper.get(V_CACHE, key) == null) {
            ghCacheHelper.setEx(V_CACHE, key, 1L, 1);
            return 1;
        }
        long sum = (long) ghCacheHelper.get(V_CACHE, key).getObjectValue();
        if (sum < vps) {
            ghCacheHelper.setEx(V_CACHE, key, sum + 1, 1);
            return 1;
        }
        return 0;
    }

    @Override
    public int uv() {
        return ghCacheHelper.sizeByPrefix(V_CACHE, UV_PREFIX);
    }
}
