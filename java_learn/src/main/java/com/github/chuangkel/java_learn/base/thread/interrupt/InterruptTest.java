package com.github.chuangkel.java_learn.base.thread.interrupt;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-12 10:23
 **/
public class InterruptTest {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                //Thread#sleep()可以响应中断标识的变化，同时抛出异常，在捕获异常的地方可以控制线程的结束
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("这里发生了中断异常");
                while(true){
                    System.out.println("hello");
                }
            }
        });
        thread.start();
        //只是修改了中断标识，并未停止线程
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
