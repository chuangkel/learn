package com.github.chuangkel.java_learn.base.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-03-15 13:51
 **/
public class Main {

    private static ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            pool.submit(new threadTest());
            ReentrantLock r;
        }
    }

    static class threadTest implements Runnable {

        @Override
        public void run() {
            SynchronizedStaticClassTest s1 = new SynchronizedStaticClassTest("1");
            s1.synchroniedStaticMethod();
        }
    }

    static class SynchronizedStaticClassTest {
        private String name;

        SynchronizedStaticClassTest(String name) {
            this.name = name;
        }

        public static synchronized void synchroniedStaticMethod() {
            System.out.println(Thread.currentThread().getName() + "hello");
        }
    }
}
