package com.github.chuangkel.java_learn.base.thread_synchronized;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-11 11:10
 **/
public class DoubleSynchronized {
    public synchronized void print(){
        synchronized (this){
            show();
        }
        System.out.println("print");
    }
    public synchronized void show(){
        System.out.println("show");
    }
}
