package com.github.chuangkel.java_learn.base.java18;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-05-24 14:32
 **/
public class Person {
    private String name;

    private String getName() {
        return name;
    }

    public static int compareName(Person a, Person b) {
        return a.getName().compareTo(b.name);
    }
}