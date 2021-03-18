package com.gahui.ghmall.server.dao;

import com.gahui.ghmall.server.DatabaseJunitTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: Gahui
 * @since: 2021/3/17
 **/
class SequenceDaoTest extends DatabaseJunitTest {

    @Autowired
    SequenceDao sequenceDao;

    @Test
    void getSeqByTableNameAndColumnName() {
        System.out.println(sequenceDao.selectByPrimaryKey(1).getColumnName());
        System.out.println(sequenceDao.getSeqByTableNameAndColumnName("gh_account","account_id").getSeqUsed());
    }
}
