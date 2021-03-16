package com.gahui.ghmall.server.mapper;

import com.gahui.ghmall.server.entity.GhOrderItem;

public interface GhOrderItemMapper {
    int deleteByPrimaryKey(Integer orderItemId);

    int insert(GhOrderItem record);

    int insertSelective(GhOrderItem record);

    GhOrderItem selectByPrimaryKey(Integer orderItemId);

    int updateByPrimaryKeySelective(GhOrderItem record);

    int updateByPrimaryKey(GhOrderItem record);
}