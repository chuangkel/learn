package com.github.chuangkel.java_learn.base.classloader;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-10 10:09
 **/
public class SuperClass {
    static{
        System.out.println("SuperClass init");
    }
    public static int VALUE = 20;
    public final static int FEILD = 20;
    //isAssignableFrom();
}
