package com.github.chuangkel.java_learn.base.collection.iterable;

/**
 * @program: learn
 * @description: 实验： for循环的本质是迭代器
 * @author: chuangkel
 * @create: 2019-10-23 10:32
 **/
public class ForTest {

    public static void main(String[] args) {
        ForObject<Object> forObject = ForObject.load(Object.class);
        for (Object str : forObject){
            System.out.println();
        }
    }
}
