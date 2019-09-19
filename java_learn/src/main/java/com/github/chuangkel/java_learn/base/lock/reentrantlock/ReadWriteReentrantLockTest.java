package com.github.chuangkel.java_learn.base.lock.reentrantlock;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-19 11:28
 **/
public class ReadWriteReentrantLockTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,1000L,
                TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(),new PersonThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        //读锁和读锁之间的关系
        for(int i = 0; i < 2; i++){
            executor.submit(()->{
                readAndRead(rwLock,cyclicBarrier);
            });
        }
    }
    public static void readAndRead(ReentrantReadWriteLock rwLock, CyclicBarrier cyclicBarrier){
        rwLock.readLock().lock();
        try {
            cyclicBarrier.await();
            for(int i = 0; i < 20; i++){
                Thread.sleep(200L);
                System.out.println("threadGroupName: " + Thread.currentThread().getThreadGroup().getName()+
                        ", threadName: "+Thread.currentThread().getName());
            }
        }catch (Exception e){
        }finally {
            rwLock.readLock().unlock();
        }
    }

    static class PersonThreadFactory implements ThreadFactory{
        ThreadGroup group = new ThreadGroup("person");
        private final AtomicInteger atomic = new AtomicInteger(1);
        private final String prefix = "pthread-";
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(group,r,prefix + atomic.getAndIncrement());
        }
    }
}
