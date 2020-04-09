package com.github.chuangkel.java_learn.base.thread.threadMutual;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-02-20 22:07
 **/
public class ReentrantLockB {

    public static void main(String[] args) throws InterruptedException {
        //线程 A 不断打印1-10的数字，打印到第5个数字时通知线程 B ，请完成编码
        ReentrantLock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Thread threadA = new Thread(() -> {
            lock.lock();
            int i = 0;
            while (true) {
                if (i == 5) {
                    //通知B线程
                    conditionB.signal(); //条件队列B转换到同步队列，并没有唤醒同步队列的线程
                    //挂起自己
                    try {
                        Thread.sleep(2000L);
                        /**fullRelease 调用await说明当前线程占用了锁，condition挂起了自己唤醒了后继者*/
                        conditionA.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(i);
                i++;
                i %= 9;
            }
        });

        Thread threadB = new Thread(() -> {
            lock.lock();
            while (true) {
                try {
                    conditionB.await();
                    System.out.println("这是B线程，收到A线程的通知");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                conditionA.signal();
                System.out.println("通知回去");
            }
        });

        threadB.start();
        threadA.start();
        //存在A唤醒了ThreadB，ThreadA还没有挂起之前，ThreadB已经signal()了ThreadA
    }
}
