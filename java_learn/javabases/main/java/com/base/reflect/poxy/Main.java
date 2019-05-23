package com.base.reflect.poxy;

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
        DynamicProxyTest d = new DynamicProxyTest(cat);
        Animal animal = (Animal)d.newProxyInstance();
        animal.eat();
    }
}
