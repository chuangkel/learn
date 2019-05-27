package com.github.chuangkel.java_learn.base.classloader;

import java.lang.reflect.Field;

/**
 * @program: personal-utils
 * @description: 检查对象参数是否为空
 * @author: chuangkel
 * @create: 2019-05-27 17:26
 **/
public class ClassT {

    public static void main(String[] args) throws NoSuchFieldException {
        PersonRequest p = new PersonRequest();
        p.setId(1);
        p.setName("name");
        fieldTest(p);
        genericity("a");
    }
    /** 判断一个对象的属性是不是都为null */
    private static boolean fieldTest(Object object) throws NoSuchFieldException {

        String simpleName = object.getClass().getSimpleName();
        String name = object.getClass().getName();
        try {
            PersonRequest pp = (PersonRequest)object.getClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Field [] fields = object.getClass().getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(object);
                if(value instanceof CharSequence){

                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println(value.toString());

            String fieldType = field.getType().getTypeName();
        }
        object.getClass().getDeclaredField("a");
        return false;
    }

    /** 泛型实践 */
    public static <T> T genericity(T t){
        T t1 = t;
        return t1;
    }
}
