package com.gahui.ghmall.server.dao;

import com.gahui.ghmall.server.dto.SequenceDto;
import com.gahui.ghmall.server.mapper.GhSequenceMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @description: 序列查询服务
 * @author: Gahui
 * @since: 2021/3/17
 **/
public interface SequenceDao extends GhSequenceMapper {

    /**
     * 通过表名和列名获取对应的序列信息
     *
     * @param tableName  表名
     * @param columnName 列名
     * @return dto
     */
    SequenceDto getSeqByTableNameAndColumnName(@Param("tableName") String tableName, @Param("columnName") String columnName);
}
