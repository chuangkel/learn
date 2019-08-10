package com.github.chuangkel.java_learn.base.bases;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-08-07 10:55
 **/
public class ThreadInterrupt {

    //public static void main(String[] args) {
    //    Thread thread = new Thread(()->{
    //        while (true){} //endless loop
    //    });
    //    thread.start();
    //    thread.interrupt(); //只改变中断状态，并没有结束线程
    //}

    //public static void main(String[] args) throws InterruptedException {
    //    Thread thread = new Thread(()->{
    //        while (!Thread.currentThread().isInterrupted()){
    //            System.out.println("sub thread is over");
    //        }
    //    });
    //    thread.start();
    //    Thread.sleep(100);
    //    thread.interrupt();
    //}

    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(100); //抛出异常之前将中断状态置为false
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().isInterrupted());
            }
        });
        thread.start();
        thread.interrupt(); //将中断状态置为true
    }
}
