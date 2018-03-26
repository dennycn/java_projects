package com.qst.chapter10.scheduler.javase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.qst.chapter10.threadpool.javase.Task;

public class Test {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

        System.out.println("开始时间：" + dateFormat.format(new Date()));
        executor.scheduleWithFixedDelay (new Task(), 1000, 1000,
                TimeUnit.MILLISECONDS);
    }
}
