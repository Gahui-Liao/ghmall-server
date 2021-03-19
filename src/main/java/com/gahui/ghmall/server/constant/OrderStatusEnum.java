package com.gahui.ghmall.server.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 订单状态枚举
 * @author: Gahui
 * @since: 2021/3/18
 **/
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    /**
     * 订单状态
     */
    NEW((byte) 0, "订单生成"),
    END((byte) 9, "订单完结");

    private Byte value;

    private String desc;
}
