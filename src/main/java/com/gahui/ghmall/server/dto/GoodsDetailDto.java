package com.gahui.ghmall.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @description: 商品详情Dto
 * @author: Gahui
 * @since: 2021/3/15
 **/
@Getter
@Setter
public class GoodsDetailDto {
    private Integer goodsId;

    private String goodsName;

    private String goodsIcon;

    private String goodsInfo;

    private Integer goodsPrice;

    private Integer goodsStock;

    private Integer goodsSold;

    private CategoryDto category;

    private List<GoodsImgDto> imgList;
}
