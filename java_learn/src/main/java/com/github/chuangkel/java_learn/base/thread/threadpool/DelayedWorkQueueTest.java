package com.github.chuangkel.java_learn.base.thread.threadpool;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-18 11:19
 **/
public class DelayedWorkQueueTest {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1,new MyThreadFactory());

        executor.scheduleAtFixedRate(()->{
            System.out.println("aaa");
        },1L,1000L,TimeUnit.MILLISECONDS);

        executor.scheduleWithFixedDelay(()->{
            System.out.println("bbb");
        },1L,1000L,TimeUnit.MILLISECONDS);
    }

    static class MyThreadFactory implements ThreadFactory {
        private ThreadGroup threadGroup = new ThreadGroup("myGroups");
        private final String prefix = "mythread";
        private final AtomicInteger threadIndex = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(threadGroup,r,prefix+threadIndex.getAndIncrement());
        }
    }
}
