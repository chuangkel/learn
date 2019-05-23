package com.base.java18;

import com.base.java18.interfaceT.IDefaultMethodImpl;
import com.base.java18.interfaceT.IDefaultMethonSon;
import com.base.java18.interfaceT.MethodInterface;

import java.util.*;

/**
 * @program: nevergiveup
 * @description:主函数方法
 * @author: yaoqb23911
 * @create: 2018-12-04
 **/
public class Main {
    public static void main(String[] args) {
        MethodInterface m = (x, y) -> System.out.println("ss" + y + " " + x);
        m.print("IntegerReplacement_397", "bb");
        System.out.println("==========================");
        HashMap<String, String> map = new HashMap<>();
        map.put("b", "1");
        map.put("c", "1");
        map.put("a","22");
        List<Map.Entry<String, String>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        for (Map.Entry<String, String> a : list) {
            System.out.println(a.getKey() + " " + a.getValue());
        }

        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("a", "b");
        System.out.println(hashtable.containsValue("b"));
        System.out.println(hashtable.get("a"));

        System.out.println("====接口默认方法覆写====");
        IDefaultMethodImpl iDefaultMethod = new IDefaultMethodImpl();
        iDefaultMethod.test();
        IDefaultMethonSon iDefaultMethonSon = new IDefaultMethonSon(){} ;
        iDefaultMethonSon.test();
        
    }
}
