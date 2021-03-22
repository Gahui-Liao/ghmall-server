package com.gahui.ghmall.server.dao;

import com.gahui.ghmall.server.mapper.GhCartMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @description: 购物车数据查询接口
 * @author: Gahui
 * @since: 2021/3/22
 **/
public interface CartDao extends GhCartMapper {

    /**
     * 根据账户标识获取购物车标识
     *
     * @param accountId 账户标识
     * @return int
     */
    Integer getCartIdByAccountId(@Param("accountId") Integer accountId);
}
