package com.github.chuangkel.java_learn.base.thread_synchronized;

import java.util.concurrent.CountDownLatch;

/**
 * @program: learn
 * @description: 两个线程A、B,打印ABABABABABABABABABAB
 * @author: chuangkel
 * @create: 2019-08-09 16:02
 **/
public class WaitNotifyTest {

    public static void main(String[] args) {
        Object object = new Object();
        int count = 10;
        CountDownLatch latch = new CountDownLatch(1);


        new Thread(() -> {
            synchronized (object) {
                for (int i = 0; i < count; i++) {

                    System.out.print("A");
                    latch.countDown();
                    object.notify();
                    if (i < count - 1) {
                        try {
                            //sleep不会放弃监视器
                            Thread.sleep(1000L);
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();


        new Thread(() -> {
            //锁升级  若 synchronized写在里面 则可能会锁升级 synchronized提到for外面去
            synchronized (object) {
                for (int i = 0; i < count; i++) {

                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print("B");
                    object.notify();
                    if (i < count - 1) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
