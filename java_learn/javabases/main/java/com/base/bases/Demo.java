package com.base.bases;

import java.io.InterruptedIOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @program: nevergiveup
 * @description:
 * @author: yaoqb23911
 * @create: 2018-12-04
 **/
public class Demo {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("IntegerReplacement_397","cc","bb");
        Collections.sort(list,(a,b)->a.compareTo(b));
        System.out.println(list);
        InterruptedIOException  i;
        Thread.interrupted();
    }
}
