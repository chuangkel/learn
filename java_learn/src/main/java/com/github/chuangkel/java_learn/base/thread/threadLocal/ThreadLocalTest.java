package com.github.chuangkel.java_learn.base.thread.threadLocal;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-03-16 09:17
 **/
public class ThreadLocalTest {

    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        threadLocal.set(new Integer(100));
        Thread.sleep(2000L);
        new Thread(() -> {
            System.out.println("子线程"+threadLocal.get());
            threadLocal.set(new Integer(200));
        }).start();

        System.out.println("主线程"+threadLocal.get());
    }
}
/**
 * 1. 每个线程私有ThreadLocal.set的值，其他线程get不到另外线程的值，
 * 2. 线程池子的场景，线程池 如果没有remove，下次执行的时候还能获取得到。
 * 3. 是怎么做到每个线程私有的，key是线程ID,Value是线程的值
 */
