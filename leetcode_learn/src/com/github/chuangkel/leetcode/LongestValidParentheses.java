package com.github.chuangkel.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @program: learn
 * @description: 32. Longest Valid Parentheses
 * @author: chuangkel
 * @create: 2019-06-04 09:43
 **/
public class LongestValidParentheses {

    public static void main(String[] args) {
        String s = "(()";
        //String s = "()(()";
        //String s = ")()())";
        System.out.println(longestValidParentheses(s));;
    }

    public static int longestValidParentheses(String s) {
        int result = 0;

        int [] arr = new int[s.length()];
        Stack<Integer> stack = new Stack<>();
        if(s == null || "".equals(s)){
            return 0;
        }
        //去除一开始不符合的
        int j = 0;
        while(')' == s.charAt(j)){
            j++;
            if(j == s.length()){
                return 0;
            }
        }
        //入栈(
        stack.add(j);

        //通过栈来进行匹配
        for(int i = j + 1; i < s.length(); i++){
            if(s.charAt(i) == ')'){
                //if匹配)
                if(!stack.empty()){
                    arr[i] = 1;
                    arr[stack.pop()] = 1;
                }
            }
            if(s.charAt(i) == '('){
                stack.add(i);
            }
        }
        int count = 0;
        for(int i = 0;i < arr.length;i++){
            if(arr[i] == 0){
                result = Math.max(count,result);
                count = 0;
                continue;
            }
            count += 1;
        }
        result = Math.max(count,result);
        return result;
    }
    /**
     * 总结：
     * 组合算法，更多的是随机应变
     *  */
}
