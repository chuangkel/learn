package com.github.chuangkel.java_learn.base.reflect.poxy.generateclass;

import com.github.chuangkel.java_learn.base.reflect.poxy.Animal;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-10-24 13:41
 **/
public class GeneratorTest {
    public static void main(String[] args) {
        String name = "GeneratedProxyClass";
        byte[] bytes = ProxyGenerator.generateProxyClass(name,new Class[]{Animal.class});
        try {
            FileOutputStream fos = new FileOutputStream(name+".class");
            fos.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
