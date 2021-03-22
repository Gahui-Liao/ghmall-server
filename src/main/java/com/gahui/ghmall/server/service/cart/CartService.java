package com.gahui.ghmall.server.service.cart;

import com.gahui.ghmall.server.vo.AcceptCartVo;
import com.github.pagehelper.PageInfo;

/**
 * @description: 购物车相关业务接口
 * @author: Gahui
 * @since: 2021/3/22
 **/
public interface CartService {

    /**
     * 根据账户标识获取购物项数据
     *
     * @param accountId 账户标识
     * @param pageNum   页号
     * @param pageSize  页大小
     * @return page
     */
    PageInfo listCartItemByAccountId(Integer accountId, Integer pageNum, Integer pageSize);

    /**
     * 将商品加入购物车
     *
     * @param acceptCartVo 前端传入加购参数
     * @return 1：成功，其他失败
     */
    int accept(AcceptCartVo acceptCartVo);
}
