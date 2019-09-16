package com.github.chuangkel.java_learn.base.thread.threadfactory.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @program: javabase
 * @description: ABCABCABC...
 * @author: chuangkel
 * @create: 2019-02-17 23:43
 **/
public class Interview5 {

    static String p = "A";
    static AtomicBoolean lock = new AtomicBoolean(true);

    public static void main(String[] args) {

        ExecutorService pool = Executors.newCachedThreadPool();
        pool.submit(new Task("A"));
        pool.submit(new Task("B"));
        pool.submit(new Task("C"));
    }

    static class Task implements Runnable {
        String permit;

        Task(String permit) {
            this.permit = permit;
        }

        @Override
        public void run() {
            synchronized (lock) {
                while (true) {
                    while (!this.permit.equals(p)) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(permit);
                    p = nextPermit(this.permit);
                    lock.notifyAll();
                }
            }
        }
    }

    static String nextPermit(String permit) {
        if ("A".equals(permit)) {
            return "B";
        }
        if ("B".equals(permit)) {
            return "C";
        }
        if ("C".equals(permit)) {
            return "A";
        }
        return null;
    }
}
