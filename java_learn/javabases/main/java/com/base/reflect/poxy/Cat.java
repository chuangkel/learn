package com.base.reflect.poxy;

/**
 * @program: javabase
 * @description: 猫
 * @author: chuangkel
 * @create: 2019-02-03 15:31
 **/
public class Cat implements Animal {
    /**
     * java 自带的动态代理 需要实现接口
     */
    @Override
    public void eat() {
        System.out.println("cat is eatting");
    }
}
