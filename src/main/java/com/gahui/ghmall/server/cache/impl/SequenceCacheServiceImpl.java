package com.gahui.ghmall.server.cache.impl;

import com.gahui.ghmall.server.cache.SequenceCacheService;
import com.gahui.ghmall.server.dto.GhSequenceDto;
import com.gahui.ghmall.server.util.GhCacheHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 序列缓存实现
 * @author: Gahui
 * @since: 2021/3/17
 **/
@Service("sequenceCacheService")
public class SequenceCacheServiceImpl implements SequenceCacheService {

    private final String ACCOUNT_SEQ_KEY = "accountSeqDto#";

    @Resource
    GhCacheHelper ghCacheHelper;

    @Override
    public GhSequenceDto getAccountSeqDto() {
        if(ghCacheHelper.get(SEQ_CACHE, ACCOUNT_SEQ_KEY) != null) {
            return (GhSequenceDto) ghCacheHelper.get(SEQ_CACHE, ACCOUNT_SEQ_KEY).getObjectValue();
        }
        return null;
    }

    @Override
    public int setAccountSeqDto(GhSequenceDto seqDto) {
        return ghCacheHelper.set(SEQ_CACHE, ACCOUNT_SEQ_KEY, seqDto);
    }
}
