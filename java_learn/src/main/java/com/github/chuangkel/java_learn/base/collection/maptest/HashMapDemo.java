package com.github.chuangkel.java_learn.base.collection.maptest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-04-21 11:14
 **/
public class HashMapDemo {

    public static void main(String[] args) {
        Map<Integer,Integer> map = new HashMap<>();

        int [] nums = {1,1,1,2,2,3};
        for(int i = 0; i < nums.length;i++){
            Integer v = map.putIfAbsent(nums[i],1);

            if(v != null){
                map.put(nums[i],map.get(nums[i]).intValue() + 1);
            }
        }
        List<Map.Entry<Integer,Integer>> result =  map.entrySet().stream().sorted((a, b)->{return b.getValue().compareTo(a.getValue());}).collect(Collectors.toList());

        int [] re = new int[result.size()];
        int i = 0;
        for(Map.Entry<Integer,Integer> entry : result){
            re[i++] = entry.getKey();
        }
        System.out.println(re.toString());
    }
}
