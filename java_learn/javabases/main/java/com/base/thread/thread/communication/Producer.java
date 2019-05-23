package com.base.thread.thread.communication;

import java.util.Queue;
import java.util.Random;

/**
 * @program: javabase
 * @description: 消费者
 * @author: chuangkel
 * @create: 2019-02-13 11:42
 **/
public class Producer implements Runnable {

    private Queue<String> queue;
    private int max = 3;
    public Producer(Queue<String> queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        //生产产品
        while (true){
            synchronized(queue){
                while (queue.size() == max){
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int a ;
                System.out.println("生产："+(a = new Random().nextInt()));
                queue.add(String.valueOf(a));
                queue.notify();
            }
        }

    }
}
