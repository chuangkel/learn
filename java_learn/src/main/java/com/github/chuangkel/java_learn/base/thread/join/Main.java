package com.github.chuangkel.java_learn.base.thread.join;

/**
 * @program: javaNewCharacteristic
 * @description:
 * @author: yaoqb23911
 * @create: 2018-12-23
 **/
public class Main {
    public static void main(String[] args) {
        Thread t = new Thread(new User());
        t.start();
    }
}
