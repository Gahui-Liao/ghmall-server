package com.gahui.ghmall.server.mapper;

import com.gahui.ghmall.server.entity.GhUser;

public interface GhUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(GhUser record);

    int insertSelective(GhUser record);

    GhUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(GhUser record);

    int updateByPrimaryKey(GhUser record);
}