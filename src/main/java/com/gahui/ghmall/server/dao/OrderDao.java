package com.gahui.ghmall.server.dao;

import com.gahui.ghmall.server.dto.OrderDto;
import com.gahui.ghmall.server.mapper.GhOrderMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 订单查询服务
 * @author: Gahui
 * @since: 2021/3/18
 **/
public interface OrderDao extends GhOrderMapper {

    /**
     * 查询账户下订单
     *
     * @param accountId 账户标识
     * @return
     */
    List<OrderDto> listOrderByAccountId(@Param("accountId") Integer accountId);
}
