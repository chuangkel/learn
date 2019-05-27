package com.github.chuangkel.java_learn.base.thread.join;

/**
 * @program: javaNewCharacteristic
 * @description:
 * @author: yaoqb23911
 * @create: 2018-12-23
 **/
public class User implements Runnable {
    @Override
    public void run() {
        Thread t = new Thread(new Customer());
        t.start();
        try {
            t.join(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("this is user");
    }
}
