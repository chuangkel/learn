package com.github.chuangkel.java_learn.base.jvm.Reference;

/**
 * @program: java_learn
 * @description: test 类
 * @author: chuangkel
 * @create: 2019-05-07 13:28
 **/
public class Employee {

    private int id;

    private String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
