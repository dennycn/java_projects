package com.qst.chapter10.threadpool.spring;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.qst.chapter10.threadpool.javase.Task;

public class Test {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "com/qst/chapter10/threadpool/spring/applicationContext.xml");

        ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) context
                .getBean("executor");

        System.out.println("开始时间：" + dateFormat.format(new Date()));
        for (int i = 0; i < 6; i++) {
            executor.execute(new Task()); // 执行
        }
    }
}
