package com.github.chuangkel.java_learn.base.clazz;

import com.sun.deploy.util.ArrayUtil;
import org.apache.commons.lang.ArrayUtils;

import java.lang.reflect.Field;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-01-10 21:26
 **/
public class Main {

    public static void main(String[] args) {
        Field [] fields = AbstractOne.class.getDeclaredFields();
        Class aClazz = AbstractOne.class;

        while ( aClazz.getSuperclass() != null){
            fields = (Field[]) ArrayUtils.addAll(fields,
                    (aClazz = aClazz.getSuperclass()).getDeclaredFields());
        }

        Field [] ii = IInterface.class.getDeclaredFields();

        Field [] intField = Integer.class.getDeclaredFields();

        Field [] strField = String.class.getDeclaredFields();
        Class clazz = String.class;
        while (clazz.getSuperclass() != null){
            strField = (Field[]) ArrayUtils.addAll(strField,
                    (clazz = clazz.getSuperclass()).getDeclaredFields());
        }

        System.out.println();
    }
}
