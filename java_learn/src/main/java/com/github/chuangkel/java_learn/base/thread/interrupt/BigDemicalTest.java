package com.github.chuangkel.java_learn.base.thread.interrupt;

import java.math.BigDecimal;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-03-07 08:37
 **/
public class BigDemicalTest {


    public static void main(String[] args) {
        BigDecimal currFacePrice = new BigDecimal("100");
        BigDecimal changePrice = new BigDecimal("5.5");
        BigDecimal lastPrice = new BigDecimal("4.2");


        BigDecimal covertValueTemp = currFacePrice.divide(changePrice,8,BigDecimal.ROUND_HALF_UP)
                .setScale(0, BigDecimal.ROUND_DOWN);
        BigDecimal leftAdd = currFacePrice.subtract(covertValueTemp.multiply(changePrice));
        //转股价值=（（可转债面额/转股价）取整）*股票最新价+小数部分
        BigDecimal covertValue = covertValueTemp
                .multiply(lastPrice)
                .add(leftAdd);
        System.out.println(covertValue);

    }
}
