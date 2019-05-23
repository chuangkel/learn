package com.base.collection;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @program: javabase
 * @description: Iterator 实践
 * @author: chuangkel
 * @create: 2019-05-15 15:22
 **/
public class IteratorTest {
    public static void main(String[] args) {
        ArrayList<String> aList = new ArrayList<String>();
        aList.add("Apple");
        aList.add("Mango");
        aList.add("Guava");
        aList.add("Orange");
        aList.add("Peach");
        System.out.println("The ArrayList elements are: ");
        for (String s : aList) {
            System.out.println(s);
        }
        Iterator i = aList.iterator();
        String str = "";
        while (i.hasNext()) {
            str = (String) i.next();
            if (str.equals("Orange")) {
                i.remove();
                System.out.println("\nThe element Orange is removed");
                break;
            }
        }
        System.out.println("\nThe ArrayList elements are: ");
        for (String s : aList) {
            System.out.println(s);
        }
    }
}
