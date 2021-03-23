package com.gahui.ghmall.server.cache;

import com.gahui.ghmall.server.constant.CacheEnum;
import com.gahui.ghmall.server.dto.SequenceDto;

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
     * 根据key获取序列信息
     *
     * @param seqKey 序列名
     * @return dto
     */
    SequenceDto getSeqDtoByKey(String seqKey);

    /**
     * 根据key设置序列信息
     *
     * @param seqDto 序列信息
     * @param seqKey key
     * @return 1：成功，其他失败
     */
    int setSeqDtoByKey(SequenceDto seqDto, String seqKey);

    /**
     * 根据枚举获取对应的唯一标识
     *
     * @param cacheEnum 枚举
     * @return int
     */
    Integer getSeqIdByEnum(CacheEnum cacheEnum);
}
