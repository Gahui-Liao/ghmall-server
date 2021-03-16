package com.gahui.ghmall.server.mapper;

import com.gahui.ghmall.server.entity.GhOrder;

public interface GhOrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(GhOrder record);

    int insertSelective(GhOrder record);

    GhOrder selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(GhOrder record);

    int updateByPrimaryKey(GhOrder record);
}