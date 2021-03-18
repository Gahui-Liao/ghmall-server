package com.gahui.ghmall.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @description: 订单Dto
 * @author: Gahui
 * @since: 2021/3/18
 **/
@Getter
@Setter
public class OrderDto {

    private Integer orderId;

    private String orderCode;

    private Byte orderStatus;

    private Long orderAmount;

    private Integer accountId;

    private List<OrderGoodsDto> orderGoodsList;
}

