package com.github.chuangkel.java_learn.base.thread.interrupt;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-10-30 19:35
 **/
public class BlockingQueueTest {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<>(3);
        try {
            abq.take();
            //在线程挂起之后再中断线程标识并不会抛出异常，检查中断标识是在线程挂起的时候检测的，
            // 如已中断，则走抛出中断异常的逻辑。
            Thread.currentThread().interrupt();
        } catch (InterruptedException e) {
            System.out.println("响应了中断啦");
            e.printStackTrace();
        }
    }
}
