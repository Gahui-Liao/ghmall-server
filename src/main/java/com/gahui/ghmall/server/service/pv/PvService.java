package com.gahui.ghmall.server.service.pv;

/**
 * @description: 访问相关业务逻辑
 * @author: Gahui
 * @since: 2021/2/5
 **/
public interface PvService {

    /**
     * 更新访问量
     * @return 1：成功，其他：失败
     */
    int updatePvCount();
}
