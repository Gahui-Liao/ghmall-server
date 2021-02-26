package com.gahui.ghmall.server.service;

import org.springframework.stereotype.Service;

/**
 * @description: 测试服务
 * @author: Gahui
 * @since: 2021/2/2
 **/
@Service("testService")
public class TestService {

    public String test() throws Exception{
        return "Hello Ghmall";
    }
}
