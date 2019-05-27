package com.github.chuangkel.java_learn.pattern.singleton;

/**
 * @program: javaNewCharacteristic
 * @description: 单例模式 测试类
 * @author: yaoqb23911
 * @create: 2018-12-26
 **/
public class SingletonTest {

    public static void main(String[] args) {
        SingletonLazyDoubleCheck s = SingletonLazyDoubleCheck.getInstance();
        SingletonLazySync.getIntance();
        SingletonLazySync.getIntance();
    }
}
