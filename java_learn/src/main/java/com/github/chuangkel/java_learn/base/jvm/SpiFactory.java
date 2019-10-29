package com.github.chuangkel.java_learn.base.jvm;



import java.util.ServiceLoader;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-10-22 21:06
 **/
public class SpiFactory {
    private static ServiceLoader<Object> spiLoader = ServiceLoader.load(Object.class);
    public static Object getSpi(String name){
        for(Object spi : spiLoader){
            System.out.println("...");
        }
        return null;
    }
}
