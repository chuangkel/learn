package com.base.jvm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-03-06 17:24
 **/
public class GcBigObjectTest {

    static Map<Integer,Date> map = new HashMap<>();
    public static void main(String[] args) {
        for(int i = 0; i < Integer.MAX_VALUE; i++){
            map.put(i,new Date());
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("hello");
        }
    }
}
