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
 * @create: 2019-07-19 19:45
 **/
public class ReentranLockTest {
    static ReentrantLock reentrantLock = new ReentrantLock();
    static Condition conditionA = reentrantLock.newCondition();
    static Condition conditionB = reentrantLock.newCondition();
    static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        PrintThread printThreadA = new PrintThread(reentrantLock, conditionA, conditionB, "A", latch);
        PrintThread printThreadB = new PrintThread(reentrantLock, conditionB, conditionA, "B", latch);
        Thread tA = new Thread(printThreadA);
        Thread tB = new Thread(printThreadB);

        tB.start();
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tA.start();
    }
}
