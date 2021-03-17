package com.gahui.ghmall.server.service.token.impl;

import com.gahui.ghmall.server.service.token.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: Gahui
 * @since: 2021/3/12
 **/
@SpringBootTest
class TokenServiceImplTest {

    @Resource
    TokenService tokenService;

    @Test
    void encode() {
    }

    @Test
    void decode() {
        System.out.println(tokenService.decode("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOjk4NzY1NCwiZXhwIjoxNjE1NTE5NDM5fQ.1XKH-MPbYFm8k8wilUUEdiEkKD9qkRny_3I4mKvvkfo"));
    }
}
