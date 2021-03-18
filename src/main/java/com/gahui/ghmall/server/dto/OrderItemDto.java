package com.gahui.ghmall.server.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 订单项Dto
 * @author: Gahui
 * @since: 2021/3/18
 **/
@Getter
@Setter
public class OrderItemDto {

    private Integer orderItemId;

    private String orderItemCode;

    private Byte orderItemStatus;

    private Integer orderId;

    private Integer goodsId;

    private Integer goodsNum;

    private GoodsDto goodsDto;
}
