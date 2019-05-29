package com.github.chuangkel.leetcode;

import java.util.ArrayList;
import java.util.List;
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

    }

    public List<Integer> findSubstring(String s, String[] words) {

        Pattern p = Pattern.compile(null);
        Matcher m = p.matcher("");
        List<Integer> indexs = new ArrayList<>();
        int index;
        while (-1 != (index = s.indexOf(words[0]))){
            indexs.add(index);
        }

        for(Integer i : indexs){

        }

        return null;
    }
}
