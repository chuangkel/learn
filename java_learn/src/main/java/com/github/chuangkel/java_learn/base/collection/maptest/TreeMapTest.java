package com.github.chuangkel.java_learn.base.collection.maptest;

import java.util.*;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-16 19:18
 **/
public class TreeMapTest {

    public static void main(String[] args) {
        TreeMap<Integer,String> treeMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        treeMap.put(3,"3");
        treeMap.put(1,"abc");
        treeMap.put(5,"cba");

        Set<Map.Entry<Integer, String>> set =  treeMap.entrySet();
        Iterator<Map.Entry<Integer, String>> it = set.iterator();
        while (it.hasNext()){
            System.out.println(it.next().toString());
        }
    }
}
