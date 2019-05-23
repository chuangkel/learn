package com.base.thread.thread;

import java.util.concurrent.*;

/**
 * @program: javabase
 * @description: test类
 * @author: chuangkel
 * @create: 2019-02-11 15:42
 **/
public class Main {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        ExecutorService pool1 = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        /**
         * 需要启动线程，启动线程关闭会导致线程池关闭
         */
        ForkJoinPool p = new ForkJoinPool();

        for (int i = 0;i < 100; i++){
            ThreadTest test = new ThreadTest(i);
//            pool.submit(test);
            p.submit(test);

        }

//        try {
//            Thread.sleep(2000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    static class ThreadTest implements Runnable{

        int a;
        public ThreadTest(int a){
            this.a = a;
        }
        @Override
        public void run() {
            System.out.println("hello "+a);
        }
    }
}
