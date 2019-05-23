package com.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * //函数要做什么 先写函数备注。
 * //已有的思维逻辑，抛弃他。
 * //写类的备注。
 * //清除自己想干什么
 *
 * @program: javaNewCharacteristic
 * @description: 179. Largest Number
 * @author: chuangkel
 * @create: 2019-01-22 15:16
 **/
public class LargestNumber_179 {
    /**
     * @param nums
     * @return 拼接成的最大数
     */
    public String largestNumber(int[] nums) {
        if (nums.length == 1) {
            return String.valueOf(nums[0]);
        }

        //排序字符串数组
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs);

        StringBuilder builder = new StringBuilder();
        //
        for (int i = strs.length - 1; i >= 0; i--) {
            boolean flag = false;
            int p = i;
            //找到i 之后小于的元素
            while (p > 0) {
                //后一个字符串包含前一个字符串，直到不包含
                if (strs[p].indexOf(strs[p - 1].substring(0, 1)) == 0) {
                    p--;
                    continue;
                }
                break;
            }
            //相邻字符串不相互包含
            if (p == i) {
                builder.append(strs[i]);
                continue;
            }
            //连续字符串包含了
            String mer = mergeStr(strs, i, p);
            builder.append(mer);

            i = p;
        }

        String result = builder.toString();
        //去掉头部的000
        int n = 0;
        for (; n < result.length(); n++) {
            if ('0' != result.charAt(n)) {
                break;
            }
        }

        return "".equals(result.substring(n)) ? "0" : result.substring(n);
    }


    /**
     * //if a > b return false; 交换处理
     * //if a < b return true;  正常处理
     *
     * @param strs
     * @param high
     * @param low
     * @return 后一个包含了前一个字符串 返回合并之后最大的字符串
     */
    public String mergeStr(String[] strs, int high, int low) {
        for (int i = low; i < high; i++) {
            //判断相邻字符串大小
            for (int j = i + 1; j <= high; j++) {
                if (judge(strs[i], strs[j])) {
                    String temp = strs[i];
                    strs[i] = strs[j];
                    strs[j] = temp;
                }
            }
        }
        String re = "";
        for (int i = high; i >= low; i--) {
            re += strs[i];
        }
        return re;
    }

    /**
     * @param a
     * @param b
     * @return true a > b, false a <= b
     */
    public boolean judge(String a, String b) {
        if (a.equals(b))
            return false;
        if ("".equals(a)) {
            return true;
        }
        if ("".equals(b)) {
            return false;
        }
        int p = 0;
        int p1 = 0;
        while (p < a.length() && p1 < b.length()
                && a.charAt(p) == b.charAt(p1)) {
            p++;
            p1++;
        }
        //初始：升序  后面字符串包含前面字符串
        if (p >= a.length() && p1 < b.length()) {
            return judge(a, b.substring(p1));
        } else if (p1 >= b.length() && p < a.length()) {
            //前面的包含后面的字符串
            return judge(a.substring(p), b);
        } else {
            //前后字符串前一部分相同 比较后一部分
            if (a.charAt(p) > b.charAt(p1)) {
                return true;
            }
        }
        return false;
    }

    //"9077746056551513321"
    @Test
    public void Test() {
//        int[] nums = {10, 2};
//        int[] nums = {3,30,34,5,9};
//        int[] nums = {128,12,320,32};
//        int[] nums = {121,12};
        int[] nums = {74, 21, 33, 51, 77, 51, 90, 60, 5, 56};
//        int[] nums = {0,0};
//        int[] nums = {1440,7548,4240,6616,733,4712,883,8,9576};
//        int[] nums = {93,51,65,84,91,78,99,71,97,9};
//        int[] nums = {999999998,999999997,999999999};
        //"9999793918478716551"
        System.out.println(largestNumber(nums));
    }

    /**
     * 别人写的
     *
     * @param num
     * @return
     */
    public String largestNumber1(int[] num) {
        if (num == null || num.length == 0)
            return "";

        // Convert int array to String array, so we can sort later on
        String[] s_num = new String[num.length];
        for (int i = 0; i < num.length; i++)
            s_num[i] = String.valueOf(num[i]);

        // Comparator to decide which string should come first in concatenation
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                String s1 = str1 + str2;
                String s2 = str2 + str1;
                return s2.compareTo(s1); // reverse order here, so we can do append() later
            }
        };

        Arrays.sort(s_num, comp);
        // An extreme edge case by lc, say you have only a bunch of 0 in your int array
        if (s_num[0].charAt(0) == '0')
            return "0";

        StringBuilder sb = new StringBuilder();
        for (String s : s_num)
            sb.append(s);

        return sb.toString();

    }
}
