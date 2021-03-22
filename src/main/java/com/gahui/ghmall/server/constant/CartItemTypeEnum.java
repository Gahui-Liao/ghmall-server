package com.gahui.ghmall.server.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 购物车操作类型
 * @author: Gahui
 * @since: 2021/3/22
 **/
@Getter
@AllArgsConstructor
public enum CartItemTypeEnum {
    /**
     * 操作类型
     */
    NEW((byte) 0, "新增"),
    INC((byte) 1, "加1"),
    DEL((byte) 2, "删除"),
    DEC((byte) 3, "减1");

    private Byte value;

    private String desc;
}
