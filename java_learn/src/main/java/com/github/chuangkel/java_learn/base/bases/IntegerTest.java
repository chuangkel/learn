package com.github.chuangkel.java_learn.base.bases;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-02-28 09:43
 **/
public class IntegerTest {

    public static void main(String[] args) {
        Integer a = 128;
        Integer b = 128;
        //超过这个-128~127范围的会存储在堆中
        System.out.println(a == b); //false
        //相同类型的包装类的比较要使用 equeals
        System.out.println(a.equals(b));//true

        Integer c = 127;
        Integer d = 127;
        Integer e = 254;
        //Integer -128~127 会存储再常量池中
        System.out.println(c == d); //true
        //表达式自动拆箱成基本数值
        System.out.println(e == (c + d)); //true
        Integer f1 = 1;
        Integer f2 = 1;
        Long f3 = 2L;
        Long f4 = 1L;
        //表达式自动拆箱成基本数值
        System.out.println(f3 == (f1 + f2)); //true
        //自动转换成Long
        System.out.println(f3.equals(f1 + f4));//true
        //会判断类型 obj instanceof Class
        System.out.println(f3.equals(f1 + f2));//false
        System.out.println(f1.equals(f4));//false
    }
}
