package com.github.chuangkel.java_learn.base.thread.thread.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: javabase
 * @description: CyclicBarrier类使用在多个线程之间的同步，每个线程run()方法中 await()方法出会挂起线程，等到指定数量的线程运行了await()挂起之后，才能都继续往下执行。
 * @author: chuangkel
 * @create: 2019-02-17 11:20
 **/
public class CyclicBarrierMain {

    private static int NUM = 10;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        CyclicBarrier lock = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("各个线程都执行完成，本线程汇总一下...");
            }
        });
        for (int i = 0; i < NUM; i++) {
            executorService.submit(new CylicBarrierTest(String.valueOf(i), lock));
        }
        executorService.shutdown();
    }

    static class CylicBarrierTest implements Runnable {
        String i;
        CyclicBarrier lock;

        CylicBarrierTest(String i, CyclicBarrier lock) {
            this.i = i;
            this.lock = lock;
        }

        @Override
        public void run() {
            System.out.println("hello" + i);
            try {
                lock.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(i + ": await之后");
        }
    }
}
