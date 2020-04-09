package com.github.chuangkel.java_learn.base.collection.stack;

import java.util.Stack;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-04-01 11:07
 **/
public class StackTest {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.pop(); //移除栈顶元素并返回其值
        stack.peek(); //查看栈顶元素 不移除
        stack.push(""); //将元素压入栈顶
        stack.search(""); //在栈中搜索，返回元素在栈中的位置，距离栈顶的位置，栈最上面元素为1，不在栈中返回-1
    }
}
