package com.github.chuangkel.java_learn.base.classloader;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-09 21:14
 **/
public class CostomClassLoaderMain {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        CostomClassLoader ccl = new CostomClassLoader();
        Class<?> personClass = Class.forName("com.github.chuangkel.java_learn.base.classloader.Person",true,ccl);
        Object object = personClass.newInstance();
        //System.out.println(((Person)object).getAge());
        System.out.println(object.getClass().getClassLoader());

    }
}
