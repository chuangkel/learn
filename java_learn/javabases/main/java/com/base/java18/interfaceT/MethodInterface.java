package com.base.java18.interfaceT;

/**
 * @program: nevergiveup
 * @description: 自定义 lambda 函数式接口
 * @author: yaoqb23911
 * @create: 2018-12-04
 **/
@FunctionalInterface
public interface MethodInterface {
    /**
     * 只能拥有一个未定义方法
     * @param v
     * @param w
     */
    public void print(String v,String w);


}
