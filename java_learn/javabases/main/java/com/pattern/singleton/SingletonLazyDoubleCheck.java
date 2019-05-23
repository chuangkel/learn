package com.pattern.singleton;

/**
 * @program: javaNewCharacteristic
 * @description: 单例模式(懒汉式 ， 双重检查加锁 ）
 * @author: yaoqb23911
 * @create: 2018-12-26
 **/
public class SingletonLazyDoubleCheck {
    //变量声明为 volatile，这就指示 JVM，这个变量是不稳定的，每次使用它都到主存中进行读取
    private static volatile SingletonLazyDoubleCheck instance;
    //单例模式必须将构造函数限制成private,只有一个构造方法
    private SingletonLazyDoubleCheck(){}
    public static SingletonLazyDoubleCheck getInstance(){
        if(instance == null){
            //为啥synchronized代码块传入参数SingletonLazyDoubleCheck.class
            synchronized(SingletonLazyDoubleCheck.class){
                if(instance == null){
                    instance = new SingletonLazyDoubleCheck();
                }
            }
        }
        return instance;
    }
}
