package com.github.chuangkel.java_learn.base.lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-12 21:02
 **/
public class LockIllegalMonitorStateExceptionTest {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        //没有lock先unlock会报IllegalMonitorStateException，好比没有在同步块里面调用wait一样
        lock.unlock();
    }
}
