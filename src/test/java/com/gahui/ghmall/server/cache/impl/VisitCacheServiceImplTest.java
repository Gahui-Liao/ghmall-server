package com.gahui.ghmall.server.cache.impl;

import com.gahui.ghmall.server.cache.VisitCacheService;
import com.gahui.ghmall.server.util.GhCacheHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.gahui.ghmall.server.cache.VisitCacheService.V_CACHE;

/**
 * @description:
 * @author: Gahui
 * @since: 2021/3/12
 **/
@SpringBootTest
class VisitCacheServiceImplTest {

    @Autowired
    VisitCacheService visitCacheService;
    @Autowired
    GhCacheHelper ghCacheHelper;

    @Test
    void putVisit() {
        System.out.println(visitCacheService.incrVisit("1111"));
        System.out.println(ghCacheHelper.get(V_CACHE,"1111"));
    }
}
