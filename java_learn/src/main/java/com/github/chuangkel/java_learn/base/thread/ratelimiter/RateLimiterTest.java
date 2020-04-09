package com.github.chuangkel.java_learn.base.thread.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-03-19 09:03
 **/
public class RateLimiterTest {

    private static final ThreadPoolExecutor executor =
            new ThreadPoolExecutor(20, 20, 1000L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
    private static final RateLimiter rateLimiter = RateLimiter.create(4);

    public static void main(String[] args) throws InterruptedException {

        List<Task> callables = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            callables.add(new Task(i));
        }
        executor.invokeAll(callables);
    }

    private static class Task implements Callable<Object>{
        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public Object call() throws Exception {
            double sleepTime = rateLimiter.acquire();
            System.out.println(i + " 等待时间：" + sleepTime);
            return null;
        }
    }
}
