package com.github.chuangkel.java_learn.base.thread.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: learn
 * @description: shutdown测试
 * @author: chuangkel
 * @create: 2019-10-30 17:29
 **/
public class ShutDownTest {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,4,1000L,TimeUnit.SECONDS
        , new ArrayBlockingQueue<>(5),new MyThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        poolExecutor.shutdown();
        //线程池shutdown 停止向阻塞队列里面提交任务
        poolExecutor.submit(()->{
            System.out.println("hello world");
        });


    }
    private static class MyThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(new ThreadGroup("ThreadGroup"),r,"ThreadName");
        }
    }
}
