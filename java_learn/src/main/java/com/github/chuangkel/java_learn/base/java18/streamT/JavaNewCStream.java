package com.github.chuangkel.java_learn.base.java18.streamT;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        /** 及早求值 而不是惰性求值 */
        List<String> list = Stream.of("a","b","c").collect(Collectors.toList());

        List<String> beginningWithNumbers = new ArrayList<>();
        for(String value : Arrays.asList("a", "1abc", "abc1")) {
            if (isDigit(value.charAt(0))) {
                beginningWithNumbers.add(value);
            }
        }
        assertEquals(Arrays.asList("1abc"), beginningWithNumbers);
        /** 改造成 .filter */
        List<String> beginningWithNumbers1
                = Stream.of("a", "1abc", "abc1")
                .filter(value -> isDigit(value.charAt(0)))
                .collect(Collectors.toList());
        assertEquals(Arrays.asList("1abc"), beginningWithNumbers1);
        /**
         * map 操作，它可用一个新的值代替 Stream 中的值。但有时，用户希望让 map
         * 操作有点变化，生成一个新的 Stream 对象取而代之。用户通常不希望结果是一连串的流，
         * 此时 flatMap 最能派上用场。
         */
        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());
        assertEquals(Arrays.asList(1, 2, 3, 4), together);
        /**
         * Stream 上常用的操作之一是求最大值和最小值。Stream API 中的 max 和 min 操作足以解决
         * 这一问题。
         * 还可以调用空 Stream 的 max 方法，返回 Optional 对象。Optional
         * 它代表一个可能存在也可能不存在的值。如果 Stream 为空，那么该值不存在，如果不为
         * 空，则该值存在。通过调用 get 方法可以取出 Optional 对象中的值。
         */
        List<Track> tracks = Arrays.asList(new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451));
        Track shortestTrack = tracks.stream()
                .min(Comparator.comparing(track -> track.getLength()))
                .get();
        /** 断言 如果不等，将抛出异常，说明了Stream最后返回的值和原来的引用是一样的，没有新创建对象*/
        assertEquals(tracks.get(1), shortestTrack);

        int count = Stream.of(1, 2, 3)
                .reduce(0, (acc, element) -> acc + element);
        assertEquals(6, count);
        /**
         * 你真的需要对外暴露一个 List 或 Set 对象吗？可能一个
         * Stream 工厂才是更好的选择。通过 Stream 暴露集合的最大优点在于，它很好地封装了内
         * 部实现的数据结构。仅暴露一个 Stream 接口，用户在实际操作中无论如何使用，都不会影
         * 响内部的 List 或 Set。
         * */

    }
    /** 对如下代码进行重构 */
    public Set<String> findLongTracks(List<Album> albums) {
        Set<String> trackNames = new HashSet<>();
        for(Album album : albums) {
            for (Track track : album.getTrackList()) {
                if (track.getLength() > 60) {
                    String name = track.getName();
                    trackNames.add(name);
                }
            }
        }
        return trackNames;
    }
    /** 重构结果 */
    public Set<String> findLongTracks1(List<Album> albums) {
        Set<String> trackNames = new HashSet<>();
        albums.forEach(album -> {
            Arrays.stream(album.getTrackList()).forEach(track->{
                if (track.getLength() > 60) {
                    String name = track.getName();
                    trackNames.add(name);
                }
            });
        });
        return trackNames;
    }

    private static boolean isDigit(char c) {
        if (c >= '0' && c <= '9'){
            return true;
        }
        return false;
    }

    public BinaryOperator<Integer> get(Integer t){

        //return new BinaryOperator<T>() {
        //    @Override
        //    public T apply(T t, T t2) {
        //        return null;
        //    }
        //};

        return (a,b)->{
            if (a > b){
                return a;
            }else{
                return b;
            }
        };
    }

    public int compute(int a,int b,BinaryOperator<Integer> binaryOperator){
        binaryOperator.apply(a,b);
        return 0;
    }
}
