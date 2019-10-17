package com.github.chuangkel.java_learn.base.java18;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-23 19:40
 **/
public class ComparetorTest {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("tom"));
        list.add(new Person("john"));
        list.add(new Person("jack"));
        //默认升序排列
        list.sort(Comparator.comparing(Person::getName));
        System.out.println(list.toString());
    }
    static class Person{
        public Person(String name) {
            this.name = name;
        }

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
