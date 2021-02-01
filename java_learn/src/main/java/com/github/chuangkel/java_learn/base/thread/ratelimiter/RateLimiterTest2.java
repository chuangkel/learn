package com.github.chuangkel.java_learn.base.thread.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lpc
 * @version $Id: RateLimiterTest2.java, v 0.1 2020年07月21日 07:33:31 lpc Exp $
 */
public class RateLimiterTest2 {
    public static void main(String[] args) {
        RateLimiter limiter = RateLimiter.create(1.0,3L, TimeUnit.SECONDS);

        ExecutorService executorService = Executors.newFixedThreadPool(50);

        executorService.execute(()->{

            double costTime = limiter.acquire(1);
            //达到 max permits , stable permits 之后， 匀速 获取到 permits
            //
            for(int i = 0;i < 20;i++){
                System.out.println(costTime);

            }
        });

    }
}
