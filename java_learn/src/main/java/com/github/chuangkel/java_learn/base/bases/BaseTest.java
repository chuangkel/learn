package com.github.chuangkel.java_learn.base.bases;

import java.io.*;
import java.util.Random;

/**
 * @program: javaNewCharacteristic
 * @description:
 * @author: chuangkel
 * @create: 2019-01-31 14:28
 **/
public class BaseTest implements Externalizable {

    public static void main(String[] args) {
        // a^b 异或 a&b 与 a|b 或
        System.out.println(1 ^ 0);
        String str1 = "Person";
        String str2 = "bbb";
        String str3 = "aaabbb";
        String str4 = "Person" + "bbb";//存储在方法区的运行时常量区
        String str5 = str1 + str2;  //存储的堆中

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
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
