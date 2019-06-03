package com.github.chuangkel.leetcode;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: learn
 * @description: 30. Substring with Concatenation of All Words
 * @author: chuangkel
 * @create: 2019-05-29 11:03
 **/
public class SubstringWithConcatenationOfAllWords {

    public static void main(String[] args) {
        String s = "wordgoodgoodgoodbestword";
        String[] words = new String[]{"word", "good", "best", "good"};

        //String s = "barfoofoobarthefoobarman";
        //String[] words = new String[]{"bar","foo","the"};

        //String s = "barfoofoobarthefoobarman";
        //String[] words = new String[]{"foo","bar"};

        //String s = "aaa";
        //String[] words = new String[]{"aa","aa"};
        System.out.println(findSubstring(s, words));
    }

    /**
     * 暴力迭代
     */
    public static List<Integer> findSubstring(String s, String[] words) {

        List<Integer> result = new ArrayList<>();

        if (s == null || "".equals(s)) {
            return result;
        }
        if (words.length == 0) {
            return result;
        }
        //数组长度
        int wordLen = words[0].length();
        //判断数组，用于判断，链表为空时，输出
        LinkedList<String> a1 = new LinkedList<>();
        a1.addAll(Arrays.asList(words));
        //回溯数组 做备份，如不能一次性检测，则将备份放回
        LinkedList<String> a2 = new LinkedList<>();
        //检测的当前位置
        int now = 0;
        for (int i = 0; i < s.length() && i + wordLen <= s.length(); ) {

            String nowString = s.substring(i, i + wordLen);
            if (a1.contains(nowString)) {
                a1.remove(nowString);
                a2.add(nowString);
                i += wordLen;

                //检测一个结束
                if (a1.size() == 0) {
                    result.add(now);
                    LinkedList<String> temp = a1;
                    a1 = a2;
                    a2 = temp;
                    now = i = now + 1;
                    continue;
                }
            } else {
                i = now += 1;
                a1.addAll(a2);
                a2.clear();
            }
        }

        return result;
    }

    public List<Integer> findSubstring1(String s, String[] words) {
        if (s.length() == 0 || words.length == 0) {
            return new ArrayList<>();
        }
        int wordLength = words[0].length();
        if (wordLength > s.length()) {
            return new ArrayList<>();
        }

        // 数组元素索引
        Map<String, Integer> indexMap = new HashMap<>();
        //相同元素计数 统计
        int[] expectedCounts = new int[words.length];
        //记录下每个词的索引，如果有重复的通过expectedCounts数组记录重复的个数，不重复的是1
        for (int i = 0; i < words.length; i++) {
            int gotten = indexMap.getOrDefault(words[i], -1);
            if (gotten == -1) {
                expectedCounts[i] = 1;
                indexMap.put(words[i], i);
            } else {
                expectedCounts[gotten] = expectedCounts[gotten] + 1;
            }
        }

        char[] chars = s.toCharArray();
        //s字符串的长度 用于记录匹配的位置,匹配的时候会记录下word在数组中的位置，不匹配时记录为-1
        int[] matches = new int[chars.length];
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            if (i >= wordLength) {
                //
                builder.deleteCharAt(0);
            }
            // 等于wordLength -1 时，进入下面的if
            builder.append(chars[i]);
            if (i >= wordLength - 1) {
                int match = indexMap.getOrDefault(builder.toString(), -1);
                matches[i - wordLength + 1] = match;
            }
        }

        List<Integer> indexMatches = new ArrayList<>();

        for (int i = 0; i < wordLength; i++) {
            int leftPosition = i;
            int[] actualCounts = new int[words.length];
            int matchTotal = 0;
            int overCount = 0;
            for (int j = 0; i + j + wordLength - 1 < chars.length; j += wordLength) {
                if (j / wordLength >= words.length) {
                    int leftIndex = matches[leftPosition];
                    if (leftIndex >= 0) {
                        int actual = actualCounts[leftIndex];
                        if (actual > expectedCounts[leftIndex]) {
                            overCount--;
                        }
                        actual--;
                        actualCounts[leftIndex] = actual;

                        matchTotal--;
                    }
                    leftPosition += wordLength;
                }

                int matchIndex = matches[i + j];

                if (matchIndex >= 0) {
                    int actual = actualCounts[matchIndex];
                    actual++;
                    actualCounts[matchIndex] = actual;
                    if (actual > expectedCounts[matchIndex]) {
                        overCount++;
                    }
                    matchTotal++;
                    if (matchTotal == words.length && overCount == 0) {
                        indexMatches.add(leftPosition);
                    }
                }
            }
        }
        return indexMatches;
    }
}
