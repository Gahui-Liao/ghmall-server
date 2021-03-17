package com.gahui.ghmall.server.dao;

import com.gahui.ghmall.server.dto.GhSequenceDto;
import com.gahui.ghmall.server.mapper.GhSequenceMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description: 序列查询服务
 * @author: Gahui
 * @since: 2021/3/17
 **/
public interface GhSequenceDao extends GhSequenceMapper {

    /**
     * 通过表名和列名获取对应的序列信息
     *
     * @param tableName  表名
     * @param columnName 列名
     * @return dto
     */
    GhSequenceDto getSeqByTableNameAndColumnName(@Param("tableName") String tableName, @Param("columnName") String columnName);
}
