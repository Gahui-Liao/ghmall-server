package com.gahui.ghmall.server.service.visit;

/**
 * @description: 访问相关业务逻辑
 * @author: Gahui
 * @since: 2021/2/5
 **/
public interface VisitService {

    /**
     * 更新独立用户访问量
     * @return 1：成功，其他：失败
     */
    int updateUv();
}
