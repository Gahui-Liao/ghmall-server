package com.gahui.ghmall.server.mapper;

import com.gahui.ghmall.server.entity.GhCartItem;

public interface GhCartItemMapper {
    int deleteByPrimaryKey(Integer cartItemId);

    int insert(GhCartItem record);

    int insertSelective(GhCartItem record);

    GhCartItem selectByPrimaryKey(Integer cartItemId);

    int updateByPrimaryKeySelective(GhCartItem record);

    int updateByPrimaryKey(GhCartItem record);
}