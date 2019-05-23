package com.base.lock;

/**
 * @program: javabase
 * @description: 锁住静态方法，测试是不是所有对象都排队
 * @author: chuangkel
 * @create: 2019-03-15 13:47
 **/
public class SynchronizedStaticClassTest {


    private String name;
    SynchronizedStaticClassTest(String name){
        this.name = name;
    }

    public static synchronized void synchroniedStaticMethod(){
        System.out.println(Thread.currentThread().getName()+"hello");
    }
}
