package com.github.chuangkel.java_learn.base.thread_synchronized;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-01-12 22:09
 **/
public class ThreadExecutionDemo {
    private static void action(){
        System.out.printf("当前线程%s\n",Thread.currentThread());
    }
    public static void main(String[] args) {
        Thread t1 = new Thread(ThreadExecutionDemo::action,"t1");
        Thread t2 = new Thread(ThreadExecutionDemo::action,"t2");
        Thread t3 = new Thread(ThreadExecutionDemo::action,"t3");
        threadStartWait(t1);
        threadStartWait(t2);
        threadStartWait(t3);
    }
    private static void threadStartWait(Thread thread) {

        if (Thread.State.NEW.equals(thread.getState())) {
            thread.start();
        }
        while (thread.isAlive()) {
            synchronized (thread) {
                try {
                    //thread.wait(); //谁来唤醒它
                    thread.wait(0); //谁来唤醒它,直到该线程销毁返回
                    //thread.join();
                    /**
                     * thread.wait() = thread.wait(0) 永久等待 直到被唤醒
                     * thread.join(); 本质上是调用wait
                     */
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
