package com.github.chuangkel.java_learn.base.thread.threadMutual;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-02-21 10:19
 **/
public class AcquireInterruptiblyTest {

    public static void main(String[] args) throws java.lang.InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread.currentThread().interrupt();
        lock.lockInterruptibly();

    }
}
