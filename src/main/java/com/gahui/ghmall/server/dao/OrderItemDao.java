package com.gahui.ghmall.server.dao;

import com.gahui.ghmall.server.dto.OrderItemDto;
import com.gahui.ghmall.server.entity.GhOrderItem;
import com.gahui.ghmall.server.mapper.GhOrderItemMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 订单项查询接口
 * @author: Gahui
 * @since: 2021/3/18
 **/
public interface OrderItemDao extends GhOrderItemMapper {

    /**
     * 根据订单标识查询订单项
     *
     * @param orderId 订单标识
     * @return list
     */
    List<OrderItemDto> listOrderItemByOrderId(@Param("orderId") Integer orderId);

    /**
     * 批量插入订单项
     *
     * @param orderItemList 订单项列表
     * @return 1：成功，其他：失败
     */
    int batchInsertOrderItem(@Param("orderItemList") List<GhOrderItem> orderItemList);
}
