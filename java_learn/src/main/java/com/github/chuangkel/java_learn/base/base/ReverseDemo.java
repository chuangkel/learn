package com.github.chuangkel.java_learn.base.base;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-04-17 17:39
 **/
public class ReverseDemo {
    public static void main(String[] args) {

        System.out.println(getReverse(1));
    }
    private static int getReverse(int a){
        if(a == 0) {
            return 0;
        }
        int result = 0;
        while(a != 0){
            result = 10 * result + (a % 10);
            a = a / 10;
        }
        return result;
    }
}
