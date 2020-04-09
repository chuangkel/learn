package com.github.chuangkel.java_learn.base.thread.threadMutual;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-02-21 09:58
 **/
public class ReentrantLockTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        lock.lock();
        for(;;){
            lock.lock();
        }
    }
}
