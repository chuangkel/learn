package com.github.chuangkel.java_learn.base.classloader;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-09 21:11
 **/
public class Person {
    private String name;

    private static int age = 20;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        Person.age = age;
    }
}
