package com.github.chuangkel.java_learn.base.bases;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-02-04 11:05
 **/
public class SimpleDemo {

    public static void main(String[] args) {
        long sum = 1;

        for(int i = 1;i <=20;i++){
            sum *= i;
        }
        System.out.println(sum);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
    }
}
