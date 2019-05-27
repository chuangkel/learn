package com.github.chuangkel.java_learn.base.classloader;

/**
 * @program: javaNewCharacteristic
 * @description: 类加载器学习实践
 * @author: chuangkel
 * @create: 2019-01-30 20:29
 **/
public class ClassLoaderTest {

    public static void main(String[] args) {
        //系统类加载器sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(ClassLoader.getSystemClassLoader());
        //扩展类加载器sun.misc.Launcher$ExtClassLoader@14ae5a5
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        //启动类加载器的父类是null
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());

        sun.misc.Launcher.getBootstrapClassPath();

        System.out.println(System.getProperty("java.class.path"));
        try {
            Class typeLoaded = Class.forName("com.base.classloader.ClassLoaderTest");
            System.out.println(typeLoaded.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        ClassLoader classLoader = new ClassLoader() {
//            @Override
//            public Class<?> loadClass(String name) throws ClassNotFoundException {
//                String name = name.substring(name.indexOf(".")-1);
//                return super.loadClass(name);
//            }
//        };
    }


    public ClassLoaderTest(String arg) {
        System.out.println(arg);
    }

    public ClassLoaderTest() {
        //指向构造器
        this("hello world");
    }


}
