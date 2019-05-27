package com.github.chuangkel.java_learn.base.thread.daemon;

//import org.junit.Test;

import org.junit.jupiter.api.Test;

/**
 * @program: javaNewCharacteristic
 * @description:
 * @author: yaoqb23911
 * @create: 2018-12-23
 **/
public class DaemonTest {
    //全局变量会被初始化为默认值
    boolean bl;//boolean 被初始化为false
    static int b;//int 被初始化为0
    DaemonThread d1 = new DaemonThread();
    DaemonThread d2 = new DaemonThread();

    //设置守护线程，在非守护线程全部退出之后，守护线程也会退出
    public static void main(String[] args) {
        System.out.println("main is running");
        Thread t = new Thread(new DaemonThread());
        t.setDaemon(true);
        t.start();
        //异或操作
        if (true ^ true) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        int a;
        System.out.println(b);
    }

    @Test//测试全局boolean 初始化值
    public void test() {
        System.out.println(bl);
    }
}
