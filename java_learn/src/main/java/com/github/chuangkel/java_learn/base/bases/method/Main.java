package com.github.chuangkel.java_learn.base.bases.method;

/**
 * @program: learn
 * @description: 形参：方法被调用时需要传递进来的参数，如：func(int a)中的a，
 * 它只有在func被调用期间a才有意义，
 * 也就是会被分配内存空间，在方法func执行完成后，a就会被销毁释放空间，也就是不存在了
 * @author: chuangkel
 * @create: 2019-10-24 14:14
 **/
public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("a");
        setName(student);
        System.out.println(student);
        System.out.println(student.getName());
    }

    private static void setName(Student student) {
        System.out.println("setName方法内部："+student.getName());
        student.setName("b");

        student = new Student();
        System.out.println("setName方法内部："+student);
        student.setName("b");
    }
}
