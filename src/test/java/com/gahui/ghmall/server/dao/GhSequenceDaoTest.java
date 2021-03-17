package com.gahui.ghmall.server.dao;

import com.gahui.ghmall.server.DatabaseJunitTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: Gahui
 * @since: 2021/3/17
 **/
class GhSequenceDaoTest extends DatabaseJunitTest {

    @Autowired
    GhSequenceDao ghSequenceDao;

    @Test
    void getSeqByTableNameAndColumnName() {
        System.out.println(ghSequenceDao.selectByPrimaryKey(1).getColumnName());
        System.out.println(ghSequenceDao.getSeqByTableNameAndColumnName("gh_account","account_id").getSeqUsed());
    }
}
