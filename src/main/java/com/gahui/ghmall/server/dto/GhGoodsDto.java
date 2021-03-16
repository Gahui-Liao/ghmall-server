package com.gahui.ghmall.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @description: 商品Dto
 * @author: Gahui
 * @since: 2021/3/15
 **/
@Getter
@Setter
public class GhGoodsDto {
    private Integer goodsId;

    private String goodsName;

    private String goodsIcon;

    private String goodsInfo;

    private Integer goodsPrice;

    private Integer goodsStock;

    private Integer goodsSold;

    private GhCategoryDto category;

    private List<GhGoodsImgDto> imgList;
}
