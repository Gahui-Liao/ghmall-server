package com.gahui.ghmall.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @description: 订单详情Dto
 * @author: Gahui
 * @since: 2021/3/18
 **/
@Getter
@Setter
public class OrderDetailDto {

    private Integer goodsId;

    private String goodsName;

    private String goodsIcon;

    private String goodsInfo;

    private Integer goodsPrice;

    private List<OrderItemDto> orderItemList;
}
