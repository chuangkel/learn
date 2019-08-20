package com.github.chuangkel.pat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-08-15 10:25
 **/
public class aa {

    public static void main(String[] args) {
        //List<String> list = new ArrayList<>();
        //list.add("one");
        //list.add("two");
        //list.add("three");
        //List<String> aa = list.subList(1,2);
        //System.out.println(aa);
        //list.add("fore");
        //for(String a : aa){
        //    System.out.println(a);
        //}
        System.out.println("=========");
        String[] str =new String [] {"you","me"};
        List<String> list2 = Arrays.asList(str);
        list2.add("her");
        for(String s : list2){
            System.out.print(s);
        }
        System.out.println(list2.get(0));
    }
}
