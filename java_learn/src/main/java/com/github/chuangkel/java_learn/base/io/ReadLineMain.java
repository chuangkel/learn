package com.github.chuangkel.java_learn.base.io;

import java.io.*;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-12-03 15:08
 **/
public class ReadLineMain {

    public static void main(String[] args) throws IOException {
        int c;
        BufferedReader reader =new BufferedReader(
                new InputStreamReader(new FileInputStream("F://a.txt")));
        System.out.println();
        String line;
        while ((line = reader.readLine()) != null){
            System.out.println(line.split("=")[1]+line.split("=")[2]);;
        }
        reader.close();
    }
}
