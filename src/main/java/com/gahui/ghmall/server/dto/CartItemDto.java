package com.gahui.ghmall.server.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 购物项Dto
 * @author: Gahui
 * @since: 2021/3/22
 **/
@Getter
@Setter
public class CartItemDto {

    private Integer cartItemId;

    private Integer cartId;

    private Integer goodsNum;

    private GoodsDto goods;
}
