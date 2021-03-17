package com.gahui.ghmall.server;

import javafx.application.Application;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * @description: 数据库单元测试基类
 * @author: Gahui
 * @since: 2021/3/17
 **/
@SpringBootTest(classes = GhmallServerApplication.class)
@ActiveProfiles("dev")
public abstract class DatabaseJunitTest {
}
