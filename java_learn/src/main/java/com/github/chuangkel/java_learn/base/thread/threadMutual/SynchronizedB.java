package com.github.chuangkel.java_learn.base.thread.threadMutual;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-02-20 23:47
 **/
public class SynchronizedB {
    public static void main(String[] args) {
        Object object = new Object();
        Thread threadA = new Thread(() -> {
            int i = 0;
            while (true) {
                synchronized (object) {
                    if (i == 5) {
                        object.notify();
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(i);
                    i++;
                    i %= 9;
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while (true) {
                synchronized (object) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("这是B");
                    object.notify();
                }
            }
        });


        threadB.start();
        threadA.start();
    }
}

