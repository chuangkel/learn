package com.github.chuangkel.java_learn.base.collection;

import java.util.*;

/**
 * @program: javaNewCharacteristic
 * @description: hashmap原理探索
 * @author: chuangkel
 * @create: 2019-01-31 09:26
 **/
public class HashMapTest {
    public static void main(String[] args) {
        //传入的初始化容量大小应该是2的次方
        // （n-1）& hash 保持随机性，n是2的次方
        HashMap map = new HashMap(256);

        map.put("b", "1");
        map.put("c", "1");
        map.put("a", "22");
        List<Map.Entry<String, String>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        //增强for循环 
        for (Map.Entry<String, String> a : list) {
            System.out.println(a.getKey() + " " + a.getValue());
        }
        work(map);

    }

    //迭代entrySet()
    public static void workByEntry(Map<String, String> map) {
        Set<Map.Entry<String, String>> set = map.entrySet();
        for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext(); ) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            System.out.println(entry.getKey() + "—>" + entry.getValue());
        }
    }

    //    keySet 使用Iterator
    public static void workByKeySet(Map<String, String> map) {
        Set<String> key = map.keySet();
        for (Iterator it = key.iterator(); it.hasNext(); ) {
            String s = (String) it.next();
            System.out.println(map.get(s));
        }
    }

    //直接迭代值
    public static void work(Map<String, String> map) {
        Collection<String> c = map.values();
        Iterator it = c.iterator();
        for (; it.hasNext(); ) {
            System.out.println(it.next());
        }
    }

}
