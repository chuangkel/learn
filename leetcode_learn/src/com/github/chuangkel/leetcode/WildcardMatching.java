package com.github.chuangkel.leetcode;/**
 * Created by Fortuner on 2019/6/7.
 */

import java.util.regex.Pattern;

/**
 * @program: learn
 * @description: 44. Wildcard Matching
 * @author: chuangkel
 * @create: 2019-06-07 10:27
 **/
public class WildcardMatching {


    public static void main(String[] args) {

        //String s = "aa";
        //String p = "a";
        String s, p;
        s = "aa";
        p = "\\*";

        System.out.println(isMatch2(s, p));
    }

    public static boolean isMatch(String s, String p) {
        if ("*".equals(p)) {
            return true;
        }
        int idx = 0;
        // * 匹配0个或者多个 ?匹配一个
        for (int i = 0; i < p.length(); i++) {
            //字符 核对
            if (p.charAt(i) != '*' && p.charAt(i) != '?') {
                if (s.charAt(idx) == p.charAt(i)) {
                    idx++;
                    continue;
                } else {
                    return false;
                }
            }
            //是？ 下一个
            if (p.charAt(i) == '?') {
                idx++;
                continue;
            }
            //是* 同时是p的最后一个
            if (p.charAt(i) == '*' && i == p.length() - 1) {
                return true;
            }
            //是* 不是最后一个 从后往前匹配 看看能否到i
            int s_indx = s.length() - 1;
            for (int j = p.length() - 1; j >= i; j--) {
                if (j == i) {
                    return true;
                }
                if (p.charAt(j) != '?') {
                    s_indx--;
                    continue;
                }
                //是字母
                if (p.charAt(j) != '?' && p.charAt(j) != '*') {
                    if (p.charAt(j) != s.charAt(s_indx)) {
                        return false;
                    }
                    s_indx --;
                    continue;
                }
                //是* 看中间匹不匹配
                String [] xings = s.split("\\*");
                for(int q = 1; q < xings.length-1  ;q++){
                }
            }
            //dao i 说明匹配
            //不到i 说明有*  split 处理 中间匹配
            return ;

        }
    }


}
