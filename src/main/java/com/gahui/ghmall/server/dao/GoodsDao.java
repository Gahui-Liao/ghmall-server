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
     * 获取商品基本信息
     *
     * @param goodsId 商品标识
     * @return dto
     */
    GoodsDto getGoodsById(@Param("goodsId") Integer goodsId);

    /**
     * 根据多个商品标识查询商品信息
     *
     * @param goodsIdList 多个商品标识
     * @return list
     */
    List<GoodsDto> listGoodsByIdList(@Param("goodsIdList") List<Integer> goodsIdList);

    /**
     * 使用乐观锁减库存
     *
     * @param goodsId     商品标识
     * @param goodsStock  商品库存
     * @param reduceNum 减少数目
     * @return 1：成功，其他：失败
     * --这里需要修改一下查询mysql连接的url，不然会一直返回为1，详见：https://www.jianshu.com/p/80270b93082a
     */
    int updateGoodsStockByIdAndNum(@Param("goodsId") Integer goodsId, @Param("goodsStock") Integer goodsStock, @Param("reduceNum") Integer reduceNum);
}
