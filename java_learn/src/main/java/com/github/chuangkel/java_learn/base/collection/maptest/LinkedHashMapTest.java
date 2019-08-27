package com.github.chuangkel.java_learn.base.collection.maptest;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-08-22 22:45
 **/
public class LinkedHashMapTest {
    /**
     *eg.[a,a,b,a,n,d,a,b,d,r]
     * 找出出现次数最多的，两个最多的情况，返回第一个
     */
    public static void main(String[] args) {
        //char[] c = new char[]{'a','b','a','c','a','c','b','b'};
        char[] c = {'a','b','a','c','a','c','b','b','b'};
        Map<Character,Integer> map = new LinkedHashMap<>();
        for(int i = 0;i < c.length; i++){
            if(map.containsKey(c[i])){
                map.put(c[i],map.get(c[i]).intValue() + 1 );
            }else{
                map.put(c[i],new Integer(1));
            }
        }
        char max_c = c[0];
        int max = 0;
        Set<Map.Entry<Character,Integer>> entries = map.entrySet();
        Iterator<Map.Entry<Character,Integer>> it = entries.iterator();
        while (it.hasNext()){
            Map.Entry<Character,Integer> temp = it.next();
            if(temp.getValue() > max){
                max_c = temp.getKey();
                max = temp.getValue();
            }
        }
        System.out.println(max_c);
    }
}
