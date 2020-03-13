package com.github.chuangkel.java_learn.base.thread_synchronized;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-11-25 21:11
 **/
public class SynchronizedTest1 {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(50,60,1000L,TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));

        System.out.println(Integer.toBinaryString(1 << 16));
        System.out.println(Integer.toBinaryString(-1 << 16));
        System.out.println(Integer.toBinaryString(-2 ));

    }

}
