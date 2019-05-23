package com.base.thread.thread.communication;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: javabase
 * @description: test类
 * @author: chuangkel
 * @create: 2019-02-13 12:00
 **/
public class Main {

    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque<>();
        ExecutorService pool = Executors.newCachedThreadPool();
        Cunstmer cunstmer = new Cunstmer(queue);
        Producer producer = new Producer(queue);
        pool.submit(cunstmer);
        pool.submit(producer);
    }
}
