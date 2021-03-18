package com.gahui.ghmall.server.dao;

import com.gahui.ghmall.server.dto.AccountDto;
import com.gahui.ghmall.server.mapper.GhAccountMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @description: 账户查询服务
 * @author: Gahui
 * @since: 2021/3/16
 **/
public interface AccountDao extends GhAccountMapper {

    /**
     * 根据账户名查询账户信息
     *
     * @param accountName 账户名
     * @return dto
     */
    AccountDto getAccountByName(@Param("accountName") String accountName);

    /**
     * 账户名是否存在
     * @param accountName 账户名
     * @return count
     */
    int countAccountByName(@Param("accountName") String accountName);

}
