package com.gahui.ghmall.server.mapper;

import com.gahui.ghmall.server.entity.GhAccount;

public interface GhAccountMapper {
    int deleteByPrimaryKey(Integer accountId);

    int insert(GhAccount record);

    int insertSelective(GhAccount record);

    GhAccount selectByPrimaryKey(Integer accountId);

    int updateByPrimaryKeySelective(GhAccount record);

    int updateByPrimaryKey(GhAccount record);
}