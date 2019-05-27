package com.github.chuangkel.java_learn.base.java18.streamT;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @program: javaNewCharacteristic
 * @description: java1.8 新特性 stream流
 * @author: yaoqb23911
 * @create: 2019-01-07
 **/
public class JavaNewCStream {

    public static void main(String[] args) {

        Arrays.stream(new int[] {1, 2, 3})
                .map(n -> 2 * n + 1)
                .average()
                .ifPresent(System.out::println);

        IntStream.range(1, 4)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);

        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);

        /** map 循环对数组进行值的转换 */
        Stream.of(1.0, 2.0, 3.0)
                .map(Double::intValue)
                .forEach(System.out::println);

        /** filter 进行过滤 */
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    if (s == "c") {
                        return false;
                    }
                    return true;
                }).forEach(System.out::println);


        /** reduce 相当于是循环 拼接 */
        String aa = Stream.of("d2", "a2", "b1", "b3", "c")
                .reduce("Q",(a,b)->{
                    return a+b;
                });
        System.out.println(aa);
        /** 第一个参数指定了返回的类型 看源码 */
        String aaa = Stream.of("d2", "a2", "b1", "b3", "c")
                .reduce("Q", (c,d)->{return c+d;},(a, b)->{
                    return a+b;
                });
        System.out.println(aaa);
    }
}
