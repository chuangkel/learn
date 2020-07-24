package com.github.chuangkel.java_learn.base.base;

import java.util.Scanner;

/**
 * @author lpc
 * @version $Id: InputTest.java, v 0.1 2020年07月14日 13:26:28 lpc Exp $
 */
public class InputTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String  ss = "";
        while(scanner.hasNext()){

            String s
             = scanner.nextLine();

            s = s.replace("+"," ").replace("\""," ");



            ss += s;


        }
        System.out.println(ss);
    }
}
