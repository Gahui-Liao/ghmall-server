package com.gahui.ghmall.server.service.goods;

import com.gahui.ghmall.server.dto.GoodsDetailDto;
import com.gahui.ghmall.server.dto.GoodsDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @description: 商品相关业务接口
 * @author: Gahui
 * @since: 2021/3/15
 **/
public interface GoodsService {

    /**
     * 根据商品标识获取商品详情
     *
     * @param goodsId 商品标识
     * @return dto
     */
    GoodsDetailDto getGoodsDetailById(Integer goodsId);

    /**
     * 分页查询推荐商品
     *
     * @param pageNum  页号
     * @param pageSize 页大小
     * @return page
     */
    PageInfo listRecommendGoods(Integer pageNum, Integer pageSize);

    /**
     * 根据商品标识获取商品基础信息
     *
     * @param goodsId 商品标识
     * @return dto
     */
    GoodsDto getGoodsBasicById(Integer goodsId);

    /**
     * 根据多个商品标识查询商品信息
     *
     * @param goodsList 多个商品标识
     * @return list
     */
    List<GoodsDto> listGoodsByIdList(List<Integer> goodsList);

    /**
     * 更新对应的商品的库存
     * @param goodsId 商品标识
     * @param goodsStock 商品库存
     * @param reduceStock 减少后库存
     * @return 1：成功，其他：失败
     */
    int updateGoodsStockByIdAndNum(Integer goodsId, Integer goodsStock, Integer reduceStock);
}
