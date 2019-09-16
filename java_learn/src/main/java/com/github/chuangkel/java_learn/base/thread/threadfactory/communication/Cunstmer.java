package com.github.chuangkel.java_learn.base.thread.threadfactory.communication;

import java.util.Queue;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-02-13 11:42
 **/
public class Cunstmer implements Runnable {

    private Queue<String> queue;

    public Cunstmer(Queue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.size() == 0) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("消费：" + queue.remove());
                queue.notify();
            }
        }
    }
}
