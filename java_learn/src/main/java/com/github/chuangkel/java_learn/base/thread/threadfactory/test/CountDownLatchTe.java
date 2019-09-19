package com.github.chuangkel.java_learn.base.thread.threadfactory.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-02-17 11:11
 **/
public class CountDownLatchTe {

    /**
     * 一个线程A等待多线程执行完成的同步，等待指定数量的线程运行了countDownLatch.countDown() 减到0 ，则A线程才能进入就绪状态，继续执行;
     * 因为当前线程要等待锁的数量(latch count)减到0，并不一定是减少锁数量的线程执行完，只需减到0就唤醒所有线程，除非当前线程发生中断
     *
     * @param args
     */

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            pool.submit(new CountDownLatchTest(countDownLatch, String.valueOf(i)));
        }
        try {
            countDownLatch.await(); // 1.若latch count 为0 则 继续执行；2.若不为0 则挂起当前线程；3.某个减少latch count到0的线程唤醒它。
            System.out.println("await()线程,任务汇总之后...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }

    static class CountDownLatchTest implements Runnable {
        CountDownLatch countDownLatch;
        String i;
        CountDownLatchTest(CountDownLatch countDownLatch, String i) {
            this.countDownLatch = countDownLatch;
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println("hello->" + i);
            countDownLatch.countDown();
            System.out.println("sleep 3000ms");
        }
    }
}
