package com.github.chuangkel.java_learn.base.bigdemical;

import java.math.BigDecimal;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-02-19 10:48
 **/
public class BigDemicalTest {

    public static void main(String[] args) {
        BigDecimal bigDecimal1 = new BigDecimal("1");
        BigDecimal bigDecimal2 = new BigDecimal("2");

        System.out.println(bigDecimal1.compareTo(bigDecimal2) > 0);

    }
}
