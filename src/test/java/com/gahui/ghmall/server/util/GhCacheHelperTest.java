package com.gahui.ghmall.server.util;

import net.sf.ehcache.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.gahui.ghmall.server.cache.VisitCacheService.V_CACHE;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: Gahui
 * @since: 2021/3/13
 **/
@SpringBootTest
class GhCacheHelperTest {
    @Autowired
    GhCacheHelper ghCacheHelper;

    @Test
    void setEx() throws InterruptedException {
        ghCacheHelper.setEx(V_CACHE,"test","7777",2);
        Element element1 =  ghCacheHelper.get(V_CACHE,"test");
        System.out.println(element1.getObjectValue());
        Thread.sleep(3000);
        Element element =  ghCacheHelper.get(V_CACHE,"test");
        System.out.println(element.getObjectValue());
    }
}
