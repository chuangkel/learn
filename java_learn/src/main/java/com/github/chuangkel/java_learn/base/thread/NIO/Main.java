package com.github.chuangkel.java_learn.base.thread.NIO;

/**
 * @program: javaNewCharacteristic
 * @description:
 * @author: yaoqb23911
 * @create: 2018-12-28
 **/
public class Main {
    public static void main(String[] args) {

        Thread thread = new Thread(new SocketTestServer());
        thread.start();

        Thread client1 = new Thread(new SocketTestClient("hello world hello world hello world hello"));
        client1.start();

        Thread client2 = new Thread(new SocketTestClient("client2 client2 client2"));
        client2.start();
    }
}
