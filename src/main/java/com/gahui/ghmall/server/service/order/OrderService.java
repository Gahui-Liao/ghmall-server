package com.gahui.ghmall.server.service.order;

import com.github.pagehelper.PageInfo;

/**
 * @description: 订单相关业务接口
 * @author: Gahui
 * @since: 2021/3/18
 **/
public interface OrderService {

    /**
     * 分页查询订单
     * @param accountId 账户标识
     * @param pageNum 页号
     * @param pageSize 页大小
     * @return page
     */
    PageInfo listOrderByAccountId(Integer accountId, Integer pageNum, Integer pageSize);
}
