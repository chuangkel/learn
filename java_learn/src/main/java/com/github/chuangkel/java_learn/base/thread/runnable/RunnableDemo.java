package com.github.chuangkel.java_learn.base.thread.runnable;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-04-12 17:13
 **/
public class RunnableDemo {
    public static void main(String[] args) {
        runRunable(()->{
            try {
                System.out.println("before");
                Thread.sleep(5000L);
                System.out.println("after");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("main");

    }
    private static void runRunable(Runnable runnable){
        runnable.run();
    }
}
