package com.gahui.ghmall.server.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: Gahui
 * @since: 2021/3/16
 **/
class AesHelperTest {

    @Test
    void encrypt() throws Exception {
        String test = "88888888";
        String key = "dffdfdfdfffdfdffdfdfff";
        String en = AesHelper.encrypt(key, test);
        System.out.println(en);
        System.out.println(AesHelper.decrypt(key,en));
    }
}
