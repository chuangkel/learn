package com.github.chuangkel.java_learn.base.reflect.poxy;

import com.github.chuangkel.java_learn.pattern.structure.proxy.staticProxy.Proxy1;

import java.lang.reflect.Proxy;

/**
 * @program: javabase
 * @description: test
 * @author: chuangkel
 * @create: 2019-02-03 15:38
 **/
public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat();
        CatInvocationHandler d = new CatInvocationHandler(cat);
        Animal animal = (Animal) d.newProxyInstance();
        animal.eat();
        animal.sport();

        //代理的 得到的对象  继承Proxy 实现接口
        System.out.println(animal instanceof Proxy);
    }
}
