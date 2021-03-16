package com.gahui.ghmall.server.mapper;

import com.gahui.ghmall.server.entity.GhGoods;

public interface GhGoodsMapper {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(GhGoods record);

    int insertSelective(GhGoods record);

    GhGoods selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(GhGoods record);

    int updateByPrimaryKey(GhGoods record);
}