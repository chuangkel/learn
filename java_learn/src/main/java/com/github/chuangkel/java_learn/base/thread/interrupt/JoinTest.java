package com.github.chuangkel.java_learn.base.thread.interrupt;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-12 10:23
 **/
public class JoinTest {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                //Thread#sleep()可以响应中断标识的变化，同时抛出异常，在捕获异常的地方可以控制线程的结束
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("thread1 这里发生了中断异常");
                while(true){
                    System.out.println("thread1 hello");
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                //Thread#sleep()可以响应中断标识的变化，同时抛出异常，在捕获异常的地方可以控制线程的结束
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                System.out.println("thread2 这里发生了中断异常");
                while(true){
                    System.out.println("thread2 hello");
                }
            }
        });
        thread2.start();
        System.out.println("1--");

        thread1.start();
        System.out.println("2--");
        //只是修改了中断标识，并未停止线程
        //thread.interrupt();
        try {
            thread2.join();
            thread1.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
