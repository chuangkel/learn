package com.github.chuangkel.java_learn.base.collection.maptest;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-11-23 14:01
 **/
public class HashMapTest {

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(hash(new HashMapTest())));

        System.out.println(Integer.toBinaryString(3));
        System.out.println(Integer.toBinaryString(5));
        //异或 只对对齐的低位进行运算 比如11 101 只进行11和01的运算
        System.out.println(Integer.toBinaryString(3^5));


    }
    //测试hashCode生成的过程
    static final int hash(Object key) {
        int h = key.hashCode();
        System.out.println(Integer.toBinaryString(h));
        //>>>无符号右移 最高位由0填充 ，>> 有符号右移 最高位由符号位填充
        System.out.println("h >>> 16\n"+Integer.toBinaryString(h*-1 >>> 16));
        System.out.println("h >> 16\n"+Integer.toBinaryString(h*-1 >> 16));
        return (key == null) ? 0 : h  ^ (h >>> 16);
    }
}
