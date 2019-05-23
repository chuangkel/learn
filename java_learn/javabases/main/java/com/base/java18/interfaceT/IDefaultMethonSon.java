package com.base.java18.interfaceT;

/**
 * @program: javabase
 * @description:
 * @author: yaoqb23911
 * @create: 2019-05-05
 **/
public interface IDefaultMethonSon extends IDefaultMethod {

    /**
     * default关键字
     */
    @Override
    default void test(){
        System.out.println("IDefaultMethodSon");
    }
}
