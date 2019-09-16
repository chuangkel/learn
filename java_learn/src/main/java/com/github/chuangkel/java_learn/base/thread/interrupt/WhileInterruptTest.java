package com.github.chuangkel.java_learn.base.thread.interrupt;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-12 19:45
 **/
public class WhileInterruptTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("i am running");
            }
            System.out.println("i am over");
        });
        thread.start();
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();

    }
}
