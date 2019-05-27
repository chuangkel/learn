package com.github.chuangkel.java_learn.base.collection.equealhashcode;

import java.time.Period;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-03-08 10:07
 **/
public class Main {

    public static void main(String[] args) {

        Object o = new Object();
        System.out.println(o.hashCode());
        Set<String> set = new HashSet<>();
        Person p1 = new Person();
        Person p2 = new Person();


    }
}
