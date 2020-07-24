package com.github.chuangkel.java_learn.base.base;

import java.util.Arrays;
import java.util.List;

/**
 * @author lpc
 * @version $Id: ListDemo.java, v 0.1 2020年07月08日 09:32:21 lpc Exp $
 */
public class ListDemo {




    public static void main(String[] args) {
        List<String> a = Arrays.asList("1","2","2","66666","2","2","1","2","2");
        List<String> left = a.subList(0,3);
        List<String> right = a.subList(3,a.size());
        System.out.println();
    }
}
