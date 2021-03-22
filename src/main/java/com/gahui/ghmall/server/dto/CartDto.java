package com.gahui.ghmall.server.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 购物车Dto
 * @author: Gahui
 * @since: 2021/3/22
 **/
@Getter
@Setter
public class CartDto {

    private Integer cartId;

    private Integer accountId;
}
