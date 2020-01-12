package com.github.chuangkel.java_learn.base.thread_synchronized;/**
 * Created by Fortuner on 2019/7/19.
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-07-19 19:47
 **/
public class PrintThread implements Runnable {
    private int COUNT = 10;
    private ReentrantLock reentrantLock;
    private Condition conditionA;
    private Condition conditionB;
    private CountDownLatch latch;
    private String c;

    PrintThread(ReentrantLock reentrantLock, Condition conditionA, Condition conditionB, String c, CountDownLatch latch) {
        this.reentrantLock = reentrantLock;
        this.conditionA = conditionA;
        this.conditionB = conditionB;
        this.c = c;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            if (c.equals("A")) {
                //保证A先加锁
                reentrantLock.lock();
                latch.countDown();
            } else {
                latch.await();
                reentrantLock.lock();//这里会等待conditionA.await()执行才能继续
            }

            for (int i = 0; i < COUNT; i++) {
                System.out.print(c);
                try {
                    conditionB.signal(); //将一个Condition节点放入等待队列，等待执行
                    if (i < COUNT - 1) {
                        conditionA.await(); //此处是关键，会将锁state清空
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
}
