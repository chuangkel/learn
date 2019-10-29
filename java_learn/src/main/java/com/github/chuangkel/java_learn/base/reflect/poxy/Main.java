package com.github.chuangkel.java_learn.base.reflect.poxy;

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
    }
}
