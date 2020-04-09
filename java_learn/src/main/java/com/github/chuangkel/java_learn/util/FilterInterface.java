package com.github.chuangkel.java_learn.util;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-03-27 13:56
 **/
public class FilterInterface {

    private final static String target = "";

    public static void main(String[] args) {

        Set<String> temp = new HashSet<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if ("-1".equals(line)) {
                break;
            }
            String[] split = line.split(" ");
            for (int i = 0; i < split.length; i++) {
                if (split[i].contains("hub_")) {
                    temp.add(split[i]);
                }
            }
        }
        List<String> list = temp.stream().map(a -> {
            if (a.contains(":")) {
                return a.split(":")[0];
            }
            return a;
        }).collect(Collectors.toSet()).stream().collect(Collectors.toList());

        for( String s : list){
            System.out.println(s);
        }

    }
}
