package com.github.chuangkel.java_learn.base.json;

import com.alibaba.dubbo.common.json.JSON;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-02-14 08:48
 **/
public class Main {

    public static void main(String[] args) {
        Map<String,List<String>> map = new HashMap<>();

        map.put("String1", Arrays.asList("a1","b1","c1"));
        map.put("String2", Arrays.asList("a2","b2","c2"));

        try {
            System.out.println(JSON.json(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.asList("a2","b2","c2"));
    }
}
