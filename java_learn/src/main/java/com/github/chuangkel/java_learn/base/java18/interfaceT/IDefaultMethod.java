package com.github.chuangkel.java_learn.base.java18.interfaceT;

/**
 * @program: nevergiveup
 * @description:
 * @author: yaoqb23911
 * @create: 2018-12-04
 **/
public interface IDefaultMethod {
    /**
     * default关键字
     */
    default void test() {
        System.out.println("IDefaultMethod");
    }
}
