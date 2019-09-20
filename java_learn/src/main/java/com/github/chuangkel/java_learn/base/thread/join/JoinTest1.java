package com.github.chuangkel.java_learn.base.thread.join;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-20 16:13
 **/
public class JoinTest1 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("join thread");
        });
        thread.start();
        thread.join();
        System.out.println("main thread");
    }
}
