package com.github.chuangkel.java_learn.mercylibz;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Fortuner on 2019/6/2.
 */
public class ShortTest {


    public static void main(String[] args) {
        //泛型 编译时检查 运行时擦除
        Set<Short> set = new HashSet<>();

        for(short i = 0;i < 100;i++){//这里用short和int占用内存一样
            //byte（8位）short(16位）int(32位）->存储的时候都是32位
            set.add(i);
            set.remove(i-1);//i 是short类型 i-1 是integer类型，所以remove空
        }
        System.out.println(set.size());//输100

        HashMap<Integer,String> hashMap = new HashMap<>();
        hashMap.get(1);//key 是object类型，只要equal相等，那就可以获取值
    }
}
