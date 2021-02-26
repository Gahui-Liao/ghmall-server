package com.gahui.ghmall.server.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 自定义线程工厂，设置线程的名称等，便于定位错误
 * @author: Gahui
 * @since: 2021/2/4
 **/
public class GhmallThreadFactory implements ThreadFactory {

    private AtomicInteger threadNum = new AtomicInteger(1);

    private String prefixName;

    public GhmallThreadFactory(String name) {
        this.prefixName = name + "-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, prefixName + threadNum.getAndIncrement());
        if (thread.isDaemon()) {
            thread.setDaemon(true);
        }
        if (thread.getPriority() != Thread.NORM_PRIORITY) {
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        return thread;
    }
}
