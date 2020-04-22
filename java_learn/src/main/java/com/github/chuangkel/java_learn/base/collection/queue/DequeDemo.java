package com.github.chuangkel.java_learn.base.collection.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-04-16 08:57
 **/
public class DequeDemo {

    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>() ;

        deque.addLast(1);
        deque.addLast(2);
        deque.offerFirst(3);
    }
}
