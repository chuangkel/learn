package com.github.springboot.domain;

/**
 * @program: springboot
 * @description:
 * @author: chuangkel
 * @create: 2019-08-28 14:13
 **/
public class Person {
    private Integer id;

    private String name;

    private Integer sex;
    private Double num;

    public Person(Integer id, String name, Integer sex, Double num) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.num = num;
    }

    public Person(Integer id, String name, Integer sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
