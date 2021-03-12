package com.gahui.ghmall.server.service.token;

/**
 * @description: token相关业务逻辑
 * @author: Gahui
 * @since: 2021/2/5
 **/
public interface TokenService {

    /**
     * 生成token
     *
     * @param accountId   账户标识，用于保存在token中
     * @return token
     */
    String encode(Long accountId);

    /**
     * token验证
     *
     * @param token token字符串
     * @return accountId
     */
    Long decode(String token);
}
