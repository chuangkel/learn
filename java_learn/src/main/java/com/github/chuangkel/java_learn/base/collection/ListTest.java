package com.github.chuangkel.java_learn.base.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-10-09 14:41
 **/
public class ListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("c");
        list.add("b");
        list.add("d");
        list.add("a");
        list.sort((a,b)->{return a.compareTo(b);});
        System.out.println(list.toString());
        for(String item : list){
            System.out.println(item);
        }

    }
}
