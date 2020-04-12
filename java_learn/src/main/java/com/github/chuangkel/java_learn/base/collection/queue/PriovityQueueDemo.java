package com.github.chuangkel.java_learn.base.collection.queue;/**
 * Created by Fortuner on 2020/4/12.
 */

import java.util.PriorityQueue;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-04-12 10:53
 **/
public class PriovityQueueDemo {

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->a.compareTo(b));

        queue.offer(3);
        queue.offer(5);
        queue.offer(2);
        queue.poll();
        queue.isEmpty();
        queue.size();
        queue.peek();

        System.out.println(queue);
    }
}
