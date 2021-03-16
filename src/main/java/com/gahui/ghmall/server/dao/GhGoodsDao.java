package com.gahui.ghmall.server.dao;

import com.gahui.ghmall.server.dto.GhGoodsDto;
import com.gahui.ghmall.server.mapper.GhGoodsMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 商品查询服务
 * @author: Gahui
 * @since: 2021/3/15
 **/
public interface GhGoodsDao extends GhGoodsMapper {

    /**
     * 根据商品标识查询商品信息
     *
     * @param goodsId 商品标识
     * @return dto
     */
    GhGoodsDto getGoodsById(@Param("goodsId") Integer goodsId);

    /**
     * 查询推荐的商品
     *
     * @return page
     */
    List<GhGoodsDto> listRecommendGoods();
}
