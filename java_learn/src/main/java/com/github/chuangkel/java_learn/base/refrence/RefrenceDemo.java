package com.github.chuangkel.java_learn.base.refrence;

import java.lang.ref.WeakReference;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-04-14 11:07
 **/
public class RefrenceDemo {

    private static WeakReference<RefrenceDemo> weekReference = null;
    public static void main(String[] args) throws InterruptedException {

        gcWeekRef();
        System.gc();
        Thread.sleep(5000L);
        System.out.println(weekReference.get());
    }

    public static void gcWeekRef() throws InterruptedException {
        weekReference = new WeakReference<>(new RefrenceDemo());
        System.out.println(weekReference.get());
        System.gc();
        System.out.println(weekReference.get());
        System.out.println(weekReference.isEnqueued());
    }
}
