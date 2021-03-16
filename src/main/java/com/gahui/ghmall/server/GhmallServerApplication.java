package com.gahui.ghmall.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @description: 启动类
 * -- @EnableCaching 启用缓存功能
 * -- @EnableScheduling 启用定时任务功能
 * -- @EnableAsync 启用异步任务功能
 * @author: Gahui
 * @since: 2021/2/2
 */
@EnableCaching
@EnableScheduling
@EnableAsync
@SpringBootApplication
@MapperScan({"com.gahui.ghmall.server.mapper", "com.gahui.ghmall.server.dao"})
public class GhmallServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GhmallServerApplication.class, args);
    }

}
