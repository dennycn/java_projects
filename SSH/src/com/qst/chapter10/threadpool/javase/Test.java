package com.qst.chapter10.threadpool.javase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 测试线程池
 */
public class Test {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");

    public static void main(String[] args) {
        // 构造线程池
        ExecutorService executor = Executors.newSingleThreadExecutor();
        System.out.println("开始时间：" + dateFormat.format(new Date()));
        for (int i = 0; i < 6; i++) {
            executor.submit(new Task()); // 执行
        }
        executor.shutdown(); // 关闭线程池

    }

    static void otherCode() {
        // 各种Executor类型：单线程（串行）、固定大小的线程池、可缓存的线程池、可调度单线程、可调度固定数线程池
        ExecutorService executor1 = Executors.newSingleThreadExecutor();
        ExecutorService executor2 = Executors.newFixedThreadPool(3);
        ExecutorService executor3 = Executors.newCachedThreadPool();
        ScheduledExecutorService executor4 = Executors
                .newSingleThreadScheduledExecutor();
        ScheduledExecutorService executor5 = Executors
                .newScheduledThreadPool(3);
    }
}
