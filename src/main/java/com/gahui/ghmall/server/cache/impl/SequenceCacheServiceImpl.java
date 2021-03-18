package com.gahui.ghmall.server.cache.impl;

import com.gahui.ghmall.server.cache.SequenceCacheService;
import com.gahui.ghmall.server.constant.CacheEnum;
import com.gahui.ghmall.server.dao.GhSequenceDao;
import com.gahui.ghmall.server.dto.GhSequenceDto;
import com.gahui.ghmall.server.entity.GhSequence;
import com.gahui.ghmall.server.util.GhCacheHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 序列缓存实现
 * @author: Gahui
 * @since: 2021/3/17
 **/
@Service("sequenceCacheService")
public class SequenceCacheServiceImpl implements SequenceCacheService {

    @Resource
    GhCacheHelper ghCacheHelper;

    @Resource
    GhSequenceDao ghSequenceDao;

    @Override
    public GhSequenceDto getSeqDtoByKey(String seqKey) {
        if (ghCacheHelper.get(SEQ_CACHE, seqKey) != null) {
            return (GhSequenceDto) ghCacheHelper.get(SEQ_CACHE, seqKey).getObjectValue();
        }
        return null;
    }

    @Override
    public int setSeqDtoByKey(GhSequenceDto seqDto, String seqKey) {
        return ghCacheHelper.set(SEQ_CACHE, seqKey, seqDto);
    }

    @Override
    public Integer getSeqIdByEnum(CacheEnum cacheEnum) {
        GhSequenceDto seqDto = this.getSeqDtoByKey(cacheEnum.getSeqPrefix());
        if (seqDto == null) {
            return this.getInitId(cacheEnum);
        }
        Integer accountId = (Integer) ghCacheHelper.get(SEQ_CACHE, cacheEnum.getIdPrefix()).getObjectValue();
        // 如果大于等于序列段最大值，则重新获取序列段
        if (accountId >= seqDto.getSeqUsed() + seqDto.getSeqStep()) {
            return this.getInitId(cacheEnum);
        }
        if (ghCacheHelper.incr(SEQ_CACHE, cacheEnum.getIdPrefix()) == 1) {
            return (Integer) ghCacheHelper.get(SEQ_CACHE, cacheEnum.getIdPrefix()).getObjectValue();
        }
        return null;
    }

    /**
     * 获取初始化的Id
     *
     * @return int
     */
    private Integer getInitId(CacheEnum cacheEnum) {
        GhSequenceDto seqDto = ghSequenceDao.getSeqByTableNameAndColumnName(cacheEnum.getTableName(), cacheEnum.getColumnName());
        this.setSeqDtoByKey(seqDto, cacheEnum.getSeqPrefix());
        ghCacheHelper.set(SEQ_CACHE, cacheEnum.getIdPrefix(), seqDto.getSeqUsed());
        GhSequence ghSequence = new GhSequence();
        BeanUtils.copyProperties(seqDto, ghSequence);
        ghSequence.setSeqUsed(seqDto.getSeqUsed() + seqDto.getSeqStep());
        ghSequenceDao.updateByPrimaryKeySelective(ghSequence);
        return seqDto.getSeqUsed() + 1;
    }
}
