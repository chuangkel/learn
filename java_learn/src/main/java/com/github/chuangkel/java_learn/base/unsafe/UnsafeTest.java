package com.github.chuangkel.java_learn.base.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-05 20:03
 **/
public class UnsafeTest {
    public static void main(String[] args) {

        Field field;
        try {
            field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe) field.get(null);
            //获取UnSafe实例,使用UnSafe实例化类
            User user = (User) unsafe.allocateInstance(User.class);
            //不会调用构造方法,默认0
            System.out.println(user.getAge());
            //修改任意实例私有变量的值
            User user1 = new User();
            System.out.println(user1.getAge());
            Field ageField = user1.getClass().getDeclaredField("age");
            unsafe.putInt(user1,unsafe.objectFieldOffset(ageField),20);
            System.out.println(user1.getAge());
            //使用堆外内存
            OffHeapArray offHeapArray = new OffHeapArray(4);
            offHeapArray.set(0, 1);
            offHeapArray.set(1, 2);
            offHeapArray.set(2, 3);
            offHeapArray.set(3, 4);
            // 在索引2的位置重复放入元素
            offHeapArray.set(2, 5);
            int sum = 0;
            for (int i = 0; i < offHeapArray.size(); i++) {
                sum += offHeapArray.get(i);
            }
            // 打印12
            System.out.println(sum);
            offHeapArray.freeMemory();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
