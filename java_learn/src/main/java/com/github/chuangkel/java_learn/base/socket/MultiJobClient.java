package com.github.chuangkel.java_learn.base.socket;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-10-18 09:35
 **/
public class MultiJobClient {

    public static void main(String[] args) {
        for(int i = 0;i<4;i++){
            new Thread(new ClientOneJob("name"+i)).start();
        }
    }
}
