package com.github.chuangkel.java_learn.base.java18;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @program: javaNewCharacteristic
 * @description: 本地时间新特性
 * @author: yaoqb23911
 * @create: 2019-01-07
 * Instant：时间戳
 * Duration：持续时间，时间差
 * LocalDate：只包含日期，比如：2016-10-20
 * LocalTime：只包含时间，比如：23:12:10
 * LocalDateTime：包含日期和时间，比如：2016-10-20 23:14:21
 * Period：时间段
 * ZoneOffset：时区偏移量，比如：+8:00
 * ZonedDateTime：带时区的时间
 * Clock：时钟，比如获取目前美国纽约的时间
 * java.time.format类
 * DateTimeFormatter：时间格式化
 **/
public class LocalTimeT {
    public static void main(String[] args) {

        //时间戳
        Instant instant = Instant.now();
        System.out.println(instant);
        //获取当前时间、日期 去掉毫秒
        LocalTime localTime = LocalTime.now().withNano(0);
        System.out.println(localTime);

        //计算天数差
        LocalDate localDate = LocalDate.now();
        LocalDate futureDay = LocalDate.of(2019, 2, 01);
        Period p = Period.between(localDate, futureDay);
        System.out.println(p.getDays());
        //日期加一天 对应的时间可以加一个小时
        LocalDate plusDay = localDate.plusDays(1);
        //LocalDate localDate1 = LocalDate.;

        localDate.minusDays(30);
        System.out.println(plusDay);
        //日期格式化
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        String time = "20190108";
        LocalDate formatDate = LocalDate.parse(time, formatter);
        formatDate.minusDays(30);
        formatDate.getYear();
        System.out.println(formatDate);
        //2019-01-08T19:42:54.050+08:00[GMT+08:00]
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
    }
}
