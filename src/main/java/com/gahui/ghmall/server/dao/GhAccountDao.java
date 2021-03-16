package com.gahui.ghmall.server.dao;

import com.gahui.ghmall.server.dto.GhAccountDto;
import org.apache.ibatis.annotations.Param;

/**
 * @description: 账户查询服务
 * @author: Gahui
 * @since: 2021/3/16
 **/
public interface GhAccountDao {

    /**
     * 根据账户名查询账户信息
     * @param accountName
     * @return
     */
    GhAccountDto getAccountByName(@Param("accountName") String accountName);
}
