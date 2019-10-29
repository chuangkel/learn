package com.github.chuangkel.java_learn.base.io;

import java.io.*;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-10-21 19:49
 **/
public class Main {
    public static void main(String[] args) throws IOException {
        int c;
        InputStream inputStream = new LowerCaseInputStream(
                new BufferedInputStream(new FileInputStream("F://a.txt")));
        while ((c = inputStream.read()) >= 0){
            System.out.print((char)c);
        }
        inputStream.close();
    }
}
