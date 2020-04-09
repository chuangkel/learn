package com.github.chuangkel.java_learn.base.interfacetest;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-02-14 08:36
 **/
public class Main {

    public static void main(String[] args) {
        TestInterface testInterface = new TestInterface();
        testInterface.setInterfaceTest(new TestInterface.InterfaceTest() {
            @Override
            public void print() {
            int [] arr = {3,5};
            }

            //@Override
            //public void close() {
            //
            //}
        });
        testInterface.setInterfaceTest(()->{
            System.out.println("hello");
        });
        testInterface.getInterfaceTest().print();
    }
}
