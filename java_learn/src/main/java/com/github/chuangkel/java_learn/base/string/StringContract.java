package com.github.chuangkel.java_learn.base.string;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-10-14 08:56
 **/
public class StringContract {

    public static void main(String[] args) {
        contract("a","b","c");
    }
    /**
     * 每一个sb.append都是一个同步方法。sb变量处于方法内部，每一个方法调用都会开辟一个新的对象，所以sb.append的锁可以消除
     * 注意：若sb作为返回值返回，那sb就是一个全局对象，从而存在并发问题。
     */
    private static String contract(String a,String b,String c){
        StringBuffer sb = new StringBuffer();
        sb.append(a);
        sb.append(b);
        sb.append(c);
        return sb.toString();
    }
}
