package com.github.chuangkel.java_learn.base.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-02-28 20:40
 **/
public class MyClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myclassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String filename = name.substring(name.lastIndexOf(".") + 1) + ".class";

                InputStream is = getClass().getResourceAsStream(filename);
                if (is == null) {
                    return super.loadClass(name);
                }
                byte[] b = null;
                try {
                    b = new byte[is.available()];
                    is.read(b);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return defineClass(name, b, 0, b.length);
            }
        };
        Object obj = myclassLoader.loadClass("com.base.classloader.MyClassLoader").newInstance();
        System.out.println(obj.getClass());
        //自定义类加载器 instanceof 相等的前提是同一个类加载器
        System.out.println(obj instanceof MyClassLoader);
    }
}
