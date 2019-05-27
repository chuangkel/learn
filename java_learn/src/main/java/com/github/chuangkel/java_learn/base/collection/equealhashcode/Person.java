package com.github.chuangkel.java_learn.base.collection.equealhashcode;

import java.util.Objects;

/**
 * @program: javabase
 * @description: hashcode equal
 * @author: chuangkel
 * @create: 2019-03-08 10:06
 **/
public class Person {

    private int age;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(age, name);
    }
}
