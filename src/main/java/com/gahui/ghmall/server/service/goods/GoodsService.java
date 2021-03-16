package com.gahui.ghmall.server.service.goods;

import com.gahui.ghmall.server.dto.GhGoodsDto;
import com.github.pagehelper.PageInfo;

/**
 * @description: 商品相关业务接口
 * @author: Gahui
 * @since: 2021/3/15
 **/
public interface GoodsService {

    /**
     * 根据商品标识获取商品详情
     * @param goodsId 商品标识
     * @return dto
     */
    GhGoodsDto getGoodsById(Integer goodsId);

    /**
     * 分页查询推荐商品
     *
     * @param pageNum 页号
     * @param pageSize 页大小
     * @return page
     */
    PageInfo listRecommendGoods(Integer pageNum, Integer pageSize);
}
