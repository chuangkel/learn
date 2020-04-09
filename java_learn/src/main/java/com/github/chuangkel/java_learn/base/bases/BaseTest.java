package com.github.chuangkel.java_learn.base.bases;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @program: javaNewCharacteristic
 * @description:
 * @author: chuangkel
 * @create: 2019-01-31 14:28
 **/
public class BaseTest implements Externalizable {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // a^b 异或 a&b 与 a|b 或
        System.out.println(1 ^ 0);
        System.out.println(3>>1);
        System.out.println(-3>>>1);
        String str1 = "aaa";
        String str2 = "bbb";
        String str3 = "aaabbb";
        String str4 = "aaa" + "bbb";//存储在方法区的运行时常量区
        String str5 = str1 + str2;  //存储的堆中 （1.7-1.8好像将常量池移到了堆中）

        float a = 2.1f;
        double e = 3.3;
        e = a;
        System.out.println(e);//输出2.0999999046325684

        System.out.println(str3 == str4);//true
        System.out.println(str3 == str5);//false
        //true intern()方法会将字符串移入常量区
        System.out.println(str3 == str5.intern());

        /** >> 和 >>> 的区别 都表示移位*/
        System.out.println(4>>1);
        System.out.println(4>>>1);
        /** 输出 -2*/
        System.out.println(-4>>1);
        /** 输出 2147483646*/
        System.out.println(-4>>>1);
        System.out.println("=====================分割线");
        System.out.println(Integer.toBinaryString((-1 << (Integer.SIZE - 3) )));


        //byte b = '1'; //输入49
        byte b = 1; //输出1
        switch (b){
            case '1':
                System.out.println("b:"+b);
                break;
            case 1:
                System.out.println("b:"+b);
                break;
        }


        System.out.println("=====================");
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("2");
        /*for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("2")) {
                list.remove(i);
            }
        }*/
        for (String s : list) {
            System.out.println("1");
            if (s.equals("2")) {
                list.remove(s);
            }
        }
        System.out.println("===============");
        System.out.println(1.0f / 0.0f); //输出 Infinity
        System.out.println(0.2 == 0.2);
        /**
         * 不可将浮点变量或者double变量用“==”或“!=”做直接比较，而应该设法转化成能用“>=”或“<=”作比较的形式。
         * 由上可知计算机在处理浮点数的时候是有误差的，所以判断两个浮点数是不是相同，是要判断是不是落在同一个区间的，
         * 这个区间就是 [-EPSINON,EPSINON] EPSINON一般很小，10的-6次方以下，这个值肯定是越小越精确。
         */
        System.out.println(Integer.MAX_VALUE);
        System.out.println("ab==============");
        String a1 = "a";
        String b1 = "b";
        String ab1 = "ab";
        String ab1new = new String("ab");
        System.out.println("ab1 == a1 + b1: " + (ab1 == a1 + b1));//false,+操作新建对象了
        System.out.println("ab1 == (a1 + b1).intern(): " + (ab1 == (a1 + b1).intern()));//false,+操作新建对象了
        System.out.println("ab1new == a1 + b1: " + (ab1new == a1 + b1));//false

        int m = 1;
        int n = m ++;
        System.out.println("N:"+n);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
