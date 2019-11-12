package com.github.chuangkel.java_learn.base.io;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-11-06 11:30
 **/
public class Column {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        for(int i = 0;i<18000;i++){
            map.put(String.valueOf(i),String.valueOf(i));
        }

        Node node;
    }

    @Override
    public String toString() {
        return "Column{}";
    }

    static private class Node{
        String name;
        Integer age;
    }

}
