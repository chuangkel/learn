package com.github.chuangkel.java_learn.base.base;

import java.util.Arrays;
import java.util.List;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-01-15 11:23
 **/
public class SubListTest
{
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,1,2,3,4,5,6,7,8,9,1,2);
        int step = 5;
        int begin = 0;
        int end = begin + step;
        while (begin < list.size()){
            if(end < list.size()){
                System.out.println("A"+list.subList(begin,end));
            }else {
                System.out.println("B"+list.subList(begin,list.size()));
            }

            begin = end;
            end = end + step;
        }

        String str = "abc";
        System.out.println(str.lastIndexOf(96)); // -1
        System.out.println(str.lastIndexOf(97)); // 0 a
        System.out.println(str.lastIndexOf(98)); // 1 b
        System.out.println(str.lastIndexOf(99)); // 2 c
        System.out.println(str.lastIndexOf(100)); // -1 d
    }
}
