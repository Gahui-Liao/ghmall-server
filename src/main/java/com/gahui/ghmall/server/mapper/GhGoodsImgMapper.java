package com.gahui.ghmall.server.mapper;

import com.gahui.ghmall.server.entity.GhGoodsImg;

public interface GhGoodsImgMapper {
    int deleteByPrimaryKey(Integer goodsImgId);

    int insert(GhGoodsImg record);

    int insertSelective(GhGoodsImg record);

    GhGoodsImg selectByPrimaryKey(Integer goodsImgId);

    int updateByPrimaryKeySelective(GhGoodsImg record);

    int updateByPrimaryKey(GhGoodsImg record);
}