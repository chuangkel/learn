package com.github.chuangkel.java_learn.base.base;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-01-09 10:05
 **/
public class DoubleTest {

    public static void main(String[] args) {
        List<Student> positionStock = Arrays.asList(new Student("a",new BigDecimal("2")),new Student("a",null));

        Map<String,Double> curAmountMap =  positionStock.stream().collect(Collectors.groupingBy(a->a.getName(),
                Collectors.summingDouble(a->a.getNum().doubleValue())));

        System.out.println();
    }
    static private class Student{
        public Student(String name, BigDecimal num) {
            this.name = name;
            this.num = num;
        }

        String name;
        BigDecimal num;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getNum() {
            return num;
        }

        public void setNum(BigDecimal num) {
            this.num = num;
        }
    }
}
