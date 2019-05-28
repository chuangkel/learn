package com.github.chuangkel.java_learn.base.bases;

import java.io.InterruptedIOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @program: nevergiveup
 * @description:
 * @author: yaoqb23911
 * @create: 2018-12-04
 **/
public class Demo {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("IntegerReplacement_397", "cc", "bb");
        Collections.sort(list, (a, b) -> a.compareTo(b));
        System.out.println(list);
        InterruptedIOException i;
        Thread.interrupted();

        /**
         * 正则表达式：总结
         * . 表示任何字符
         * * 表示没有或者更多个前面的字符
         * ^ 表示开头
         * $ 表示结尾
         * ? 表示0个或者1个
         * + 表示至少一个或更多
         */
        Boolean b = Pattern.matches(".*01$","11101");
        System.out.println(b);
    }
}
