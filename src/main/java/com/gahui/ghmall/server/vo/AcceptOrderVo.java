package com.gahui.ghmall.server.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @description: 订单Vo
 * @author: Gahui
 * @since: 2021/3/18
 **/
@Getter
@Setter
public class AcceptOrderVo {

    private Integer accountId;

    private List<OrderItemVo> orderItemList;
}

