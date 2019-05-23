package com.pattern.singleton;

/**
 * @program: javaNewCharacteristic
 * @description: 单例模式(饿汉式,线程安全)
 * @author: yaoqb23911
 * @create: 2018-12-26
 **/
public class SingletonHungrySync {
    final static SingletonHungrySync instance = new SingletonHungrySync();
    //单例模式必须将构造函数限制成private,只有一个构造方法
    private SingletonHungrySync(){}

    public static SingletonHungrySync getInstance(){
        return instance;
    }
}
