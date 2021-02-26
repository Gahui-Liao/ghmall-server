package com.gahui.ghmall.server.cache.impl;

import com.gahui.ghmall.server.cache.BaseEhCacheService;
import com.gahui.ghmall.server.cache.PvCacheService;
import org.springframework.stereotype.Service;

/**
 * @description: 访问相关缓存业务逻辑
 * @author: Gahui
 * @since: 2021/2/5
 **/
@Service("pvCacheService")
public class PvCacheServiceImpl extends BaseEhCacheService implements PvCacheService {
    @Override
    public int updatePvCount() throws Exception {
        return 0;
    }
}
