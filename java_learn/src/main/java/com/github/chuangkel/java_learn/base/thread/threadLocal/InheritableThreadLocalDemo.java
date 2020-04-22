package com.github.chuangkel.java_learn.base.thread.threadLocal;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-04-14 18:29
 **/
public class InheritableThreadLocalDemo {
    static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    static ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
    public static void main(String[] args) {
        inheritableThreadLocal.set("父线程 : ThreadLocal 会传递到子线程");
        threadLocal.set("父线程 : ThreadLocal 不会传递到子线程");
        threadLocal1.set("父线程 : ThreadLocal 不会传递到子线程");
        Thread thread1 = new Thread(() -> {
            System.out.println(inheritableThreadLocal.get());
            threadLocal.set("abc");
            threadLocal1.set("def");
            System.out.println(threadLocal.get());
            System.out.println(threadLocal1.get());
        });
        thread1.start();
    }
}
