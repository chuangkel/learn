package com.github.chuangkel.java_learn.base.generic;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-01-20 22:01
 **/
public class SubClass implements SupperClass<String> {
    @Override
    public void method(String s) {
        System.out.println(s);
    }
}
