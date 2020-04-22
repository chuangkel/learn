package com.github.chuangkel.java_learn.base.base;

import java.math.BigDecimal;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-01-12 11:06
 **/
public class BigdemicalTest {

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("12.3456789");

        BigDecimal a = bigDecimal.setScale(2,BigDecimal.ROUND_DOWN);
        System.out.println();
    }
}
