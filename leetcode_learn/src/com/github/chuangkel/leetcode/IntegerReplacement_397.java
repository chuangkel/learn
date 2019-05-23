package com.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @program: javaNewCharacteristic
 * @description: 397. Integer Replacement
 * @author: chuangkel
 * @create: 2019-01-17
 * 1
 * 2 =1
 * 3 选择-1 =2
 * 4 =2
 * 5 选择-1 =3
 * 6 =3
 * 7 选择+1 -1 =4
 * 8 =3
 * 9 选择-1 =4
 * 10 =4
 * 11 选择-1 =5
 * 12 =4
 * 39 40/38 20/19 10/18 5/9 1.奇数怎么处理 +、-  2. 偶数怎么处理
 * 510 255 区间 512 1024
 * 512 256        511 513 515
 * 514 257
 * 516 258 129
 * 518 259
 * ===
 * 1024 512 256 128 64 16 4 2 1
 * 512  256 128 64  16 4  2 1
 * 768 384 192 96 48 24 12 6 3 2 1
 * 767 766 383 382                           256 512 中间384
 **/
public class IntegerReplacement_397 {
    public int integerReplacement(int n) {
        Map<Integer, Integer> map = new HashMap<>();

        if (n == Integer.MAX_VALUE) {
            return 1 + circle(n - 1, map);
        }
        return circle(n, map);
    }

    public int circle(int num, Map<Integer, Integer> map) {
        if (map.containsKey(num))
            return map.get(num);
        int key = num;
        int count = 0;
        while (num % 2 == 0) {
            num /= 2;
            count++;
        }
        if (num == 1) {
            map.put(key, count);
            return count;
        }
        if (map.containsKey(num)) {
            return map.get(num) + count;
        }
        int re1 = circle(num - 1, map);
        int re2 = circle(num + 1, map);
        int min = re1 < re2 ? re1 : re2;
        min++;
        return min + count;
    }

    @Test
    public void test() {
//        int a = integerReplacement(9);
//        int a = integerReplacement(8);
//        int a = integerReplacement(65535);
//        int a = integerReplacement(6);
        int a = integerReplacement(10000);
//        int a = integerReplacement(2147483647);
//        int a = integerReplacement(7);
//        int a = integerReplacement(100000000);
//        int a = integerReplacement(9);
//        int a = integerReplacement(11);
//        int a = integerReplacement(2147483647);
        System.out.println(a);
        System.out.println(Integer.MAX_VALUE + 1);
        System.out.println(Math.pow(2, 16));
        System.out.println(true ? 1 : 1 + 1);
    }
}
