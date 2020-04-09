package com.github.chuangkel.java_learn.base.thread;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-02-15 10:55
 **/
public class ThreadNums {

    public static void main(String[] args) {
        Thread thread  = new Thread(()->{
            System.out.println("hello world");
        });
        thread.start();

        thread.start();

        System.out.println("");
    }
}
