package com.base.thread.daemon;

/**
 * @program: javaNewCharacteristic
 * @description:
 * @author: yaoqb23911
 * @create: 2018-12-23
 **/
public class DaemonThread implements Runnable {
    @Override
    public void run() {
        for(int i = 0;i<Integer.MAX_VALUE;i++){
            System.out.println(i);
        }
    }
}
