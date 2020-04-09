package com.github.chuangkel.java_learn.base.collection.set;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-02-18 20:19
 **/
public class TreeSetMain {

    public static void main(String[] args) {
        TreeSet<String> set = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //返回0 元素重复
                return o1.compareTo(o2);
            }
        });
        set.add("DefualtClassLoader");
        set.add("EefualtClassLoader");

        Map map;
        Collection collection;
        Hashtable hashtable;
        HashMap hashMap;
        TreeSet treeSet;


        Map<String,String> map1 = new HashMap<>();

        map1.put("124","aaa");
        map1.put("123","bbb");

        List<String> set1 = map1.keySet().stream().sorted((a,b)->a.compareTo(b)).collect(Collectors.toList());

        System.out.println(set1.toString());

    }
}
