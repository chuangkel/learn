package com.github.chuangkel.java_learn.base.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @program: learn
 * @description:
 * s（秒）、ms（毫秒）、μs（微秒）、ns（纳秒），
 * 其中：1s=1000ms，1 ms=1000μs，1μs=1000ns
 * @author: chuangkel
 * @create: 2019-10-15 10:42
 **/
public class DateDemo {
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.getNano();
        System.out.printf("%s-%s-%s %s:%s:%s %s\n",localDateTime.getYear(),localDateTime.getMonth(),localDateTime.getDayOfMonth()
                ,localDateTime.getHour(),localDateTime.getMinute(),localDateTime.getSecond()
                ,localDateTime.getNano());
        System.out.println(localDateTime.getNano());

        //时间转字符串格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        String dateTime = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);
        System.out.println(dateTime);
        //格式化字符串转时间
        String dataTimeStrFormat = "2019-10-15 11:07:22 417";
        DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        LocalDateTime dateTime11 = LocalDateTime.parse(dataTimeStrFormat, df1);
        System.out.println(dateTime11);
        //字符串转时间
        String dateTimeStr = "2018-07-28 14:11:15";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime1 = LocalDateTime.parse(dateTimeStr, df);
        System.out.println(dateTime1+"\n ====================");

        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        LocalDateTime dateTime2 = Instant.ofEpochMilli(milliSecond).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        System.out.println(milliSecond+"\n=====================");
        System.out.println(dateTime2+"\n ==========");


        long timestamp = System.currentTimeMillis();
        System.out.println("timestamp===== "+ (timestamp - begin));
        Instant.ofEpochMilli(timestamp - begin).toEpochMilli();
        //LocalDate localDate = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();
        LocalDateTime localDateTime2 = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        System.out.println(localDateTime2);

    }
}
