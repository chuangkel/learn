package com.github.chuangkel.java_learn.google;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-16 15:41
 **/
public class DelimterTest {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(5);
        ThreadPoolExecutor pools = new ThreadPoolExecutor(30, 30, 1000L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 30; i++) {
            pools.submit(() -> {
                System.out.println(rateLimiter.acquire());
            });
        }
    }
}
