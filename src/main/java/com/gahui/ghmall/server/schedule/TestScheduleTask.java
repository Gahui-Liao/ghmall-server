package com.gahui.ghmall.server.schedule;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @description: 定时任务测试
 * @author: Gahui
 * @since: 2021/2/4
 **/
@Component
public class TestScheduleTask {

    @Async("scheduleExecutor")
    @Scheduled(cron = "0/5 * * * * ?")
    public void test(){
//        System.out.println("测试定时任务");
//        throw new IllegalArgumentException("测试定时任务抛出异常");
    }
}
