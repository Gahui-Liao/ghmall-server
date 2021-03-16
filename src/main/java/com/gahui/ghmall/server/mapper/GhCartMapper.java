package com.gahui.ghmall.server.mapper;

import com.gahui.ghmall.server.entity.GhCart;

public interface GhCartMapper {
    int deleteByPrimaryKey(Integer cartId);

    int insert(GhCart record);

    int insertSelective(GhCart record);

    GhCart selectByPrimaryKey(Integer cartId);

    int updateByPrimaryKeySelective(GhCart record);

    int updateByPrimaryKey(GhCart record);
}