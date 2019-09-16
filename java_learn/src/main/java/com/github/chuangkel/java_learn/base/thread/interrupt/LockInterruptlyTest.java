package com.github.chuangkel.java_learn.base.thread.interrupt;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-12 19:58
 **/
public class LockInterruptlyTest {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        //new Thread(()->{
        //    lockTest(lock,"first");
        //}).start();

        Thread thread = new Thread(() -> {
            lockTest(lock,"second");
        });
        thread.start();
        Thread.sleep(100L);
        thread.interrupt();//线程没启动时，设置中断标识是无效的
        System.out.println(thread.isInterrupted());;
    }

    private static void lockTest(Lock lock,String string){
        System.out.println(string);
        try {
            lock.lockInterruptibly();
            while (true){
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    /**
     * 结论：
     * 如果使用lockInterruptly方式获取了锁，在其同步块期间，其他线程中断了这个线程，并不会抛出InterruptionExption
     * lockInterruptly只有在尝试获取锁的而又没有获取锁的时候，其线程中断标识为true,才会抛出InterruptionExeption
     */
}

