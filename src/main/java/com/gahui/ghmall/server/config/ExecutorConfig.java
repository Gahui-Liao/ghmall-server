package com.gahui.ghmall.server.config;

import com.gahui.ghmall.server.util.GhmallThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: 线程池配置类
 * @author: Gahui
 * @since: 2021/2/4
 **/
@Configuration
public class ExecutorConfig {

    /**
     * 定时任务线程池
     */
    @Bean
    public ExecutorService scheduleExecutor() {
        return new ThreadPoolExecutor(5,
                10,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1000),
                new GhmallThreadFactory("Ghmall-ScheduleThread"),
                new ThreadPoolExecutor.AbortPolicy());
    }
}

