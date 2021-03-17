package com.gahui.ghmall.server.mapper;

import com.gahui.ghmall.server.entity.GhSequence;

public interface GhSequenceMapper {
    int deleteByPrimaryKey(Integer seqId);

    int insert(GhSequence record);

    int insertSelective(GhSequence record);

    GhSequence selectByPrimaryKey(Integer seqId);

    int updateByPrimaryKeySelective(GhSequence record);

    int updateByPrimaryKey(GhSequence record);
}