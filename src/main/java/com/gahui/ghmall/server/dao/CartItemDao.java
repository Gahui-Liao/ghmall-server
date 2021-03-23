package com.gahui.ghmall.server.dao;

import com.gahui.ghmall.server.dto.CartItemDto;
import com.gahui.ghmall.server.mapper.GhCartItemMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 购物项数据查询接口
 * @author: Gahui
 * @since: 2021/3/22
 **/
public interface CartItemDao extends GhCartItemMapper {

    /**
     * 根据账户标识查询购物项
     *
     * @param accountId 账户标识
     * @return list
     */
    List<CartItemDto> listCartItemByAccountId(@Param("accountId") Integer accountId);

    /**
     * 根据购物车标识和商品标识获取购物项信息
     *
     * @param cartId  购物车标识
     * @param goodsId 商品标识
     * @return dto
     */
    CartItemDto getCartItemByCartIdAndGoodsId(@Param("cartId") Integer cartId, @Param("goodsId") Integer goodsId);

    /**
     * 增加购物项商品数目
     *
     * @param cartItemId 购物项标识
     * @param goodsNum   商品数目
     * @return 1：成功，其他：失败
     */
    int increaseCartItem(@Param("cartItemId") Integer cartItemId, @Param("goodsNum") Integer goodsNum);

    /**
     * 减少购物项商品数目
     *
     * @param cartItemId 购物项标识
     * @param goodsNum   商品数目
     * @return 1：成功，其他：失败
     */
    int decreaseCartItem(@Param("cartItemId") Integer cartItemId, @Param("goodsNum") Integer goodsNum);

    /**
     * 批量删除购物项
     * @param accountId 账户标识
     * @param goodsIdList 商品标识列表
     * @return 0：失败，其他成功
     */
    int batchDeleteCartItem(@Param("accountId") Integer accountId, @Param("goodsIdList")List<Integer> goodsIdList);
}
