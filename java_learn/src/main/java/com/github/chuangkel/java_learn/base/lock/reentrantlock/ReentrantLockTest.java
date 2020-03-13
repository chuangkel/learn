package com.github.chuangkel.java_learn.base.lock.reentrantlock;

import java.math.BigDecimal;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-12-25 17:40
 **/
public class ReentrantLockTest {
    private static  ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(20,30,1000L,TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5));

        lock.tryLock(1000L,TimeUnit.MILLISECONDS);

        lock.unlock();

        BigDecimal bd = new BigDecimal( "23452.4523434" );
        BigDecimal fractionalPart = bd.remainder( BigDecimal.ONE ); // Result:  0.4523434

        System.out.println(fractionalPart.toString());
        System.out.println(123.456%1);
    }
}
