package com.gahui.ghmall.server.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 商品简要信息Dto
 * @author: Gahui
 * @since: 2021/3/18
 **/
@Getter
@Setter
public class GoodsDto {

    private Integer goodsId;

    private String goodsName;

    private String goodsIcon;

    private String goodsInfo;

    private Integer goodsPrice;
}
