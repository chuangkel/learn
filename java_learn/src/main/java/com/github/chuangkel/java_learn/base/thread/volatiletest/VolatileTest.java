package com.github.chuangkel.java_learn.base.thread.volatiletest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-03-20 10:41
 **/
public class VolatileTest {

    private volatile static int volatile_i = 0;
    /**
     * 有自旋重试的操作 volatile只是保证了读取的值最新 而不保证更新值之后重试
     * public final int getAndAddInt(Object var1, long var2, int var4) {
     * int var5;
     * do {
     * var5 = this.getIntVolatile(var1, var2);
     * } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
     * <p>
     * return var5;
     * }
     */
    private static AtomicInteger atomicInteger_i = new AtomicInteger(0);
    private static Counter counter_i = new Counter();
    private static ExecutorService pools = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        for (int j = 0; j < 1000; j++) {
            pools.submit(new Runnable() {
                @Override
                public void run() {
                    /**
                     * i++ 分为三个步骤 读取i 、加1、 将值写回i
                     */
//                    volatile_i ++;
                    atomicInteger_i.getAndIncrement();
//                    counter_i.addI();
                }
            });
        }

        try {
            //主线程睡眠 等待自加线程运行结束
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pools.shutdown();

        System.out.println("volatile_i  " + volatile_i);
        System.out.println("atomicInteger_i  " + atomicInteger_i);
        System.out.println("counter_i  " + counter_i.getI());

    }


    private static class Counter {
        private int i = 0;

        public int getI() {
            return i;
        }

        public synchronized void addI() {
            this.i++;
        }

    }
}
