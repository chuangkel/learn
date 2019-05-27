package com.github.chuangkel.java_learn.base.java18;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

/**
 * @program: javaNewCharacteristic
 * @description: java1.8新特性  函数式编程
 * @author: yaoqb23911
 * @create: 2019-01-08
 **/
public class LambdaT {

    public static void main(String[] args) {
        //所有的匿名类都可以改成函数式编程
        //第一种，
        Thread t = new Thread(() -> System.out.println("hell! it is running"));
        t.start();
        //第二种，
        BinaryOperator<Integer> count = (Integer x, Integer y) -> x + y;
        System.out.println(count.apply(1, 2));
        //第三种，
        BinaryOperator<Long> cc = (x, y) -> x + y;
        System.out.println(cc.apply(2L, 3L));
        //第四种，
        ActionListener actionListener = event -> System.out.println("is clicked");
        Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));

        /** 方法引用 */
        List<Person> list = new ArrayList<>();
        list.sort(Person::compareName);

        Person[] persons = new Person[1];
        Arrays.sort(persons, Person::compareName);

    }
}
