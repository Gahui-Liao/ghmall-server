package com.gahui.ghmall.server.cache;

import com.gahui.ghmall.server.dto.GhSequenceDto;

/**
 * @description: 序列缓存接口
 * @author: Gahui
 * @since: 2021/3/17
 **/
public interface SequenceCacheService {

    /**
     * 用于访问业务逻辑的缓存名称
     * 需在ehcaceh.xml中对缓存进行配置
     */
    String SEQ_CACHE = "s_cache";

    /**
     * 获取账号序列信息
     * @return dto
     */
    GhSequenceDto getAccountSeqDto();

    /**
     * 更新账号序列环迅信息
     * @param seqDto 序列dto
     * @return 1：成功，其他：失败
     */
    int setAccountSeqDto(GhSequenceDto seqDto);
}
