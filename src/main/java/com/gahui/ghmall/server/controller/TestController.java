package com.gahui.ghmall.server.controller;

import com.gahui.ghmall.server.exception.GhmallException;
import com.gahui.ghmall.server.service.TestService;
import com.gahui.ghmall.server.vo.ResponseVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 测试controller
 * @author: Gahui
 * @since: 2021/2/2
 **/
@RestController
public class TestController {
    @Resource
    TestService testService;

    @RequestMapping("/test")
    public ResponseVo test() {
        try {
            throw new NullPointerException();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GhmallException(e);

        }

//        return ResponseVo.success();
    }
}
