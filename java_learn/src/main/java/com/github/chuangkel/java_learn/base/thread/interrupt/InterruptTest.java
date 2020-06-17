package com.github.chuangkel.java_learn.base.thread.interrupt;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-12 10:23
 **/
public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("进入 sleep ...");
                /**
                 *Thread#sleep()可以随时响应中断标识的变化，同时抛出{@link InterruptedException}异常。
                 * 在捕获异常的地方可以控制线程的结束
                 */
                Thread.sleep(1500000);
                System.out.println("子线程新来了, 若被中断应该不会打印该行...");
            } catch (InterruptedException e) {
                System.out.println("这里发生了中断异常");
            }
        });
        //启动之后才能中断
        //thread.interrupt();

        thread.start();
        //主线程 sleep 1 s 保证子线程进入sleep
        Thread.sleep(1000);

        System.out.println("开始中断...");
        /**
         * 1. {@link Thread#interrupt()}只是修改了中断标识，并未停止线程
         * 2. 已经进入sleep的线程可以被interrupt。
         * 3. 中断进入sleep线程时,sleep线程能立刻响应。
         */
        thread.interrupt();
        System.out.println("已经中断...");
        thread.join();
        //中断sleep线程 它立刻响应了 立刻返回了
        System.out.println("游戏结束...");
    }
}
