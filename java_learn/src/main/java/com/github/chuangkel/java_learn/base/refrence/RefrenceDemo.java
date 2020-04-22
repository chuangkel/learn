package com.github.chuangkel.java_learn.base.refrence;

import java.lang.ref.WeakReference;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-04-14 11:07
 **/
public class RefrenceDemo {

    public static void main(String[] args) throws InterruptedException {

        WeakReference<String> weekReference = new WeakReference<>("abc");
        System.out.println(weekReference.get());
        System.gc();
        Thread.sleep(10000L);
        System.out.println(weekReference.get());
        System.out.println(weekReference.isEnqueued());
    }
}
