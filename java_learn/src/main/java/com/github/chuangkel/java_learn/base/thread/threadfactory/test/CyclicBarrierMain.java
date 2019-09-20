package com.github.chuangkel.java_learn.base.thread.threadfactory.test;

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

    public static void main(String[] args) {
        int NUM = 3;
        ExecutorService executorService = Executors.newCachedThreadPool();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUM, ()->{
            System.out.println("各个线程都执行完成，本线程汇总一下...");
        });
        for (int i = 0; i < NUM; i++) {
            executorService.submit(new CylicBarrierTest(String.valueOf(i), cyclicBarrier));
        }
        executorService.shutdown();
    }

    static class CylicBarrierTest implements Runnable {
        private String i;
        private CyclicBarrier cyclicBarrier;

        CylicBarrierTest(String i, CyclicBarrier cyclicBarrier) {
            this.i = i;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("hello" + i);
            try {
                cyclicBarrier.await(); //多线程在这里停止
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(i + ": await之后");
        }
    }
}
