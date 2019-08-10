package com.github.chuangkel.java_learn.mercylibz;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-07-26 16:56
 **/
public class MapTest {

    final static Map<String,String> unmap;

    static {
        Map<String,String> map = new HashMap<>();
        map.put("a","b");
        map.put("ab","bb");
        //该方法传入map,返回不可以修改映射的map
        unmap = Collections.unmodifiableMap(map);
    }
    public static void main(String[] args) {
        System.out.println(unmap.toString());
    }
}
