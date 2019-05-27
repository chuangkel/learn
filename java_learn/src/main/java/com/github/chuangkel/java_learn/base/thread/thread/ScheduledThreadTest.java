package com.github.chuangkel.java_learn.base.thread.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-02-28 14:54
 **/
public class ScheduledThreadTest {

    public static void main(String[] args) {
        ExecutorService pool2 = Executors.newScheduledThreadPool(5);
        /**
         * 定时线程  每个两秒执行一次 延迟一秒执行
         */
        ((ScheduledExecutorService) pool2).scheduleAtFixedRate(new Main.ThreadTest(1), 1,
                2, TimeUnit.SECONDS);
    }

    static class ThreadTest implements Runnable {

        int a;

        public ThreadTest(int a) {
            this.a = a;
        }

        @Override
        public void run() {
            System.out.println("hello " + a);
        }
    }
}
