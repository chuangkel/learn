package com.github.chuangkel.java_learn.base.base;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * 日期间隔  计算
 */
public class Main {
    public static void main(String[] arg) {
        String begin = "1990-01-01";
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine().trim();

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        try {
            LocalDate beginDate = LocalDate.parse(begin, formatter);
            LocalDate input = LocalDate.parse(line, formatter);
            if (input.isBefore(beginDate)) {
                System.out.println("Invalid input");
            }

            //long p = DAYS.between(day0, day1);
            long p = beginDate.until(input, DAYS);
            int result = (int) p % 5;

            if (0 <= result && result < 3) {
                System.out.println("He is working");
            } else {
                System.out.println("He is having a rest");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid input");
        }
    }
}