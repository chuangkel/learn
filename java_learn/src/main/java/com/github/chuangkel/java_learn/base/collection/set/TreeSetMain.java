package com.github.chuangkel.java_learn.base.collection.set;

import java.util.*;

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
        Map map;
        Collection collection;
        Hashtable hashtable;
        HashMap hashMap;
        TreeSet treeSet;
    }
}
