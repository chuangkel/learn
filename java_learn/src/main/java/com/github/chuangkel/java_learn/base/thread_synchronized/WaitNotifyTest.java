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


        new Thread(()->{
            for(int i = 0; i< count;i++){
                synchronized (object) {
                    System.out.print("A");
                    latch.countDown();
                    object.notify();
                    if(i < count -1){
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(()->{
            for(int i = 0; i< count;i++){
                synchronized (object) {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print("B");
                    object.notify();
                    if(i < count -1){
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
