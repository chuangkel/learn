package com.github.chuangkel.java_learn.base.jvm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-02-27 20:49
 **/
public class DeadLock {
    static ExecutorService service = Executors.newCachedThreadPool();
    static Map<String, String> mapA = new HashMap<>();
    static Map<String, String> mapB = new HashMap<>();

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            new Thread(new Task()).start();
            new Thread(new Task()).start();
        }

        runTask runTask = new runTask();
        runTask runTask2 = new runTask();
        runTask.start();
        runTask2.start();

        service.submit(runTask);
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            synchronized (mapA) {
                synchronized (mapB) {
                    System.out.println("hello ");
                }
            }
        }
    }

    static class runTask extends Thread {
        @Override
        public void run() {

        }
    }


}
