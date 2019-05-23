package com.base.java18.streamT;

import java.util.stream.Stream;

/**
 * @program: javaNewCharacteristic
 * @description: java1.8 新特性 stream流
 * @author: yaoqb23911
 * @create: 2019-01-07
 **/
public class JavaNewCStream {

    public static void main(String[] args) {
//        Arrays.stream(new int[] {1, 2, 3})
//                .map(n -> 2 * n + 1)
//                .average()
//                .ifPresent(System.out::println);
//
//        IntStream.range(1, 4)
//                .mapToObj(i -> "a" + i)
//                .forEach(System.out::println);
//
//        Stream.of(1.0, 2.0, 3.0)
//                .mapToInt(Double::intValue)
//                .mapToObj(i -> "a" + i)
//                .forEach(System.out::println);
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    if(s == "c") {
                        return false;
                    }
                    return true;
                }).forEach(System.out::println);
    }
}
