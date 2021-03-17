package com.gahui.ghmall.server.cache.impl;

import com.gahui.ghmall.server.cache.AccountCacheService;
import com.gahui.ghmall.server.cache.SequenceCacheService;
import com.gahui.ghmall.server.dao.GhSequenceDao;
import com.gahui.ghmall.server.dto.GhSequenceDto;
import com.gahui.ghmall.server.entity.GhSequence;
import com.gahui.ghmall.server.util.GhCacheHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 账户类缓存实现
 * @author: Gahui
 * @since: 2021/3/16
 **/
@Service("accountCacheService")
public class AccountCacheServiceImpl implements AccountCacheService {

    private final String ACCOUNT_ID_PREFIX = "accountId#";

    @Resource
    SequenceCacheService sequenceCacheService;

    @Resource
    GhSequenceDao ghSequenceDao;

    @Resource
    GhCacheHelper ghCacheHelper;

    @Override
    public Integer getAccountId() {
        GhSequenceDto seqDto = sequenceCacheService.getAccountSeqDto();
        if (seqDto == null) {
            seqDto = ghSequenceDao.getSeqByTableNameAndColumnName("gh_account", "account_id");
            sequenceCacheService.setAccountSeqDto(seqDto);
            ghCacheHelper.set(ACCOUNT_CACHE, ACCOUNT_ID_PREFIX, seqDto.getSeqUsed());
        }
        Integer accountId = (Integer) ghCacheHelper.get(ACCOUNT_CACHE, ACCOUNT_ID_PREFIX).getObjectValue();
        // 如果大于等于账户标识序列段最大值，则重新获取序列段
        if (accountId >= seqDto.getSeqUsed() + seqDto.getSeqStep()) {
            seqDto = ghSequenceDao.getSeqByTableNameAndColumnName("gh_account", "account_id");
            sequenceCacheService.setAccountSeqDto(seqDto);
            ghCacheHelper.set(ACCOUNT_CACHE, ACCOUNT_ID_PREFIX, seqDto.getSeqUsed());
            GhSequence ghSequence = new GhSequence();
            BeanUtils.copyProperties(seqDto, ghSequence);
            ghSequence.setSeqUsed(seqDto.getSeqUsed() + seqDto.getSeqStep());
            ghSequenceDao.updateByPrimaryKeySelective(ghSequence);
            return seqDto.getSeqUsed() + 1;
        }
        if (ghCacheHelper.incr(ACCOUNT_CACHE, ACCOUNT_ID_PREFIX) == 1) {
            return (Integer) ghCacheHelper.get(ACCOUNT_CACHE, ACCOUNT_ID_PREFIX).getObjectValue();
        }
        return null;
    }
}
