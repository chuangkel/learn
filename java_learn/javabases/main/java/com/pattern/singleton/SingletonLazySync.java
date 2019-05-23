package com.pattern.singleton;

/**
 * @program: javaNewCharacteristic
 * @description: 单例模式（饿汉式，synchronized 锁住方法)
 * @author: yaoqb23911
 * @create: 2018-12-26
 **/
public class SingletonLazySync {

    private static SingletonLazySync instance;
    //单例模式必须将构造函数限制成private,只有一个构造方法
    private SingletonLazySync(){}

    public synchronized static SingletonLazySync getIntance(){
        if(instance == null){
            instance = new SingletonLazySync();
        }
        return instance;
    }
}
