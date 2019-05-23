package com.base.jvm.Reference;

import java.util.WeakHashMap;

/**
 * @program: java_learn
 * @description: WeekHashMap  实践
 * @author: chuangkel
 * @create: 2019-05-07 13:34
 **/
public class Main {

    public static void main(String[] args) {
        /**
         * WeakHashMap key 值的对象指向null ,该 key - value 将被回收
         */
        WeakHashMap weekHashMap = new WeakHashMap<Employee,Integer>();
        Employee tom = new Employee(1,"tom");
        Employee saly = new Employee(2,"saly");
        weekHashMap.put(tom,1);
        weekHashMap.put(saly,2);
        System.out.println(weekHashMap.toString());
        tom = null;
        System.gc();
        System.out.println(weekHashMap);
        // 现象 第一次WeakHashMap<Integer,Employee> 的时候 tom 对象没有被回收
        WeakHashMap weakHashMap1 = new WeakHashMap<Integer,Employee>();
        Employee tom1 = new Employee(1,"tom1");
        Employee saly1 = new Employee(2,"saly1");
        weakHashMap1.put(1,tom1);
        weakHashMap1.put(2,saly1);
        System.out.println(weakHashMap1);
        tom1 = null;
        System.gc();
        System.out.println(weakHashMap1);
    }
}
