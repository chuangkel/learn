package com.base.thread.join;

/**
 * @program: javaNewCharacteristic
 * @description:
 * @author: yaoqb23911
 * @create: 2018-12-23
 **/
public class Customer implements Runnable {
    @Override
    public void run() {
        System.out.println("Customer");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
