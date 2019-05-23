package com.base.thread.thread;

/**
 * @program: javabase
 * @description: ThreadLocal 测试
 * @author: chuangkel
 * @create: 2019-03-21 10:52
 **/
public class ThreadLocalTestt {

    private static ThreadLocal<String> t = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        t.set("我在main()中set");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程 before:"+t.get());
                t.set("我在子线程中set()");
                System.out.println("子线程 after:"+t.get());
            }
        });
        thread.start();
        thread.join();

        System.out.println("main线程after:" + t.get());


    }


}
