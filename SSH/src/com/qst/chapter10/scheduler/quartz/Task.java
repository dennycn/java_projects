package com.qst.chapter10.scheduler.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 任务类
 */
public class Task {

    // 格式化时间用
    static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");

    public void doSomeThing() {
        try {
            String msg = "任务线程：" + Thread.currentThread().getName() + "，执行时间："
                    + dateFormat.format(new Date());
            Thread.sleep(500); // 模拟耗时操作
            msg += " ~ " + dateFormat.format(new Date());
            System.out.println(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
