package com.gahui.ghmall.server.dao;

import com.gahui.ghmall.server.dto.GoodsDetailDto;
import com.gahui.ghmall.server.dto.GoodsDto;
import com.gahui.ghmall.server.mapper.GhGoodsMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 商品查询服务
 * @author: Gahui
 * @since: 2021/3/15
 **/
public interface GoodsDao extends GhGoodsMapper {

    /**
     * 根据商品标识查询商品信息
     *
     * @param goodsId 商品标识
     * @return dto
     */
    GoodsDetailDto getGoodsDetailById(@Param("goodsId") Integer goodsId);

    /**
     * 查询推荐的商品
     *
     * @return page
     */
    List<GoodsDto> listRecommendGoods();

    /**
     * 校验商品是否存在
     *
     * @param goodsId 商品标识
     * @return int
     */
    int validateGoodsById(@Param("goodsId") Integer goodsId);

    /**
     * 获取商品进本信息
     *
     * @param goodsId 商品标识
     * @return dto
     */
    GoodsDto getGoodsById(@Param("goodsId") Integer goodsId);
}
