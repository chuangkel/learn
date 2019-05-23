package com.base.reflect;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-02-02 13:00
 **/
public class ReflectTest {

    public static void main(String[] args) {
        Class c= String.class;
        Class d  = c.getClass();
        System.out.println(c);
        System.out.println(d);
    }
}
