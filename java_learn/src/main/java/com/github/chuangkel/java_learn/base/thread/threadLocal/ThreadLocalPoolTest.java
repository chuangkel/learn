package com.github.chuangkel.java_learn.base.thread.threadLocal;

import com.github.chuangkel.java_learn.pattern.action.state.allState.Run;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-03-16 09:25
 **/
public class ThreadLocalPoolTest {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 500L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));

        executor.submit(() -> {
            System.out.println(threadLocal.get());
            threadLocal.set("1");
            System.out.println(threadLocal.get());
            threadLocal.remove();
        });

        executor.submit(() -> {
            System.out.println(threadLocal.get());
            threadLocal.set("2");
        });


        executor.submit(() -> {
            System.out.println(threadLocal.get());
            threadLocal.set("3");
        });
    }
}
