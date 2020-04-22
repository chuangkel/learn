package com.github.chuangkel.java_learn.base.base;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-17 09:11
 **/
public class MoveTest {
    /**
     * 原码、反码、补码
     * 一个负数是取其绝对值的源码按位取反得到反码然后加1得到补码，即负数的二进制
     * -3 取3的原码000...011，反码111...100,补码111...101 = -3
     * @param args
     */

    public static void main(String[] args) {

        //1000
        System.out.println(Integer.toBinaryString(1<<3));
        //0
        System.out.println(Integer.toBinaryString(1>>3));
        //10000
        System.out.println(Integer.toBinaryString(2<<3));
        //11000
        System.out.println(Integer.toBinaryString(3 << 3));
        //100...000(29个0)
        System.out.println(Integer.toBinaryString(1<<29));
        System.out.println("========-1的二进制=======");
        //111...111 (32个1)
        System.out.println(Integer.toBinaryString(-1));
        //111...110
        System.out.println(Integer.toBinaryString(-2));
        //11111111111111111111111111111101
        System.out.println(Integer.toBinaryString(-3));
        //11111111111111111111111111111010
        System.out.println(Integer.toBinaryString(-3 << 1));
        //11111111111111111111111111111110
        System.out.println(Integer.toBinaryString(-3 >> 1));
        //11100000000000000000000000000000 (29个0)
        System.out.println(Integer.toBinaryString(-1 << 29));
        //100000000000000000000000000000
        System.out.println(Integer.toBinaryString(1 << 29));
        //1000000000000000000000000000000
        System.out.println(Integer.toBinaryString(2 << 29));
        //1100000000000000000000000000000
        System.out.println(Integer.toBinaryString(3 << 29));
    }
}
