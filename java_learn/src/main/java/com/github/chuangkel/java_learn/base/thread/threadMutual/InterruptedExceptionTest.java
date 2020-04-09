package com.github.chuangkel.java_learn.base.thread.threadMutual;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-02-21 10:28
 **/
public class InterruptedExceptionTest {

    public static void main(String[] args) throws java.lang.InterruptedException {

        Thread.currentThread().interrupt();
        Thread.sleep(1000L);

    }
}
