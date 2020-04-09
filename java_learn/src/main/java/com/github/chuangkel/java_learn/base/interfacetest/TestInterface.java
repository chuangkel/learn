package com.github.chuangkel.java_learn.base.interfacetest;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-02-14 08:36
 **/
public class TestInterface {

    private InterfaceTest interfaceTest;

    public InterfaceTest getInterfaceTest() {
        return interfaceTest;
    }

    public void setInterfaceTest(InterfaceTest interfaceTest) {
        this.interfaceTest = interfaceTest;
    }

    public interface InterfaceTest {
        void print();

        //void close();
    }
}
