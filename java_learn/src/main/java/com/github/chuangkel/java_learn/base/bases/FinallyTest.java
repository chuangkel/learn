package com.github.chuangkel.java_learn.base.bases;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-12 15:49
 **/
public class FinallyTest {
    public static void main(String[] args) {
        try {
            return;
        }catch (Exception e){

        }finally {
            System.out.println("must across here");
        }
    }

}
