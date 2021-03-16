package com.gahui.ghmall.server.mapper;

import com.gahui.ghmall.server.entity.GhCategory;

public interface GhCategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(GhCategory record);

    int insertSelective(GhCategory record);

    GhCategory selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(GhCategory record);

    int updateByPrimaryKey(GhCategory record);
}