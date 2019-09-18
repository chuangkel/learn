package com.github.chuangkel.java_learn.base.collection.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-17 16:35
 **/
public class BlockedQueueTest {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(4);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,5,1000L,TimeUnit.MILLISECONDS
        ,new LinkedBlockingDeque<>(10));

        for(int i = 0;i < 20 ;i ++){
            executor.execute(()->{
                queue.offer("123");
            });
        }
    }
}
