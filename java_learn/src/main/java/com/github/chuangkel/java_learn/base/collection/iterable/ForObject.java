package com.github.chuangkel.java_learn.base.collection.iterable;

import java.util.Iterator;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-10-23 10:34
 **/
public class ForObject<T> implements Iterable<T> {


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }
    public static <T> ForObject<T> load(Class<T> clazz){
        ForObject<T> t = new ForObject<>();
        return t;
    }
    public ForObject<T> load1(){
        return null;
    }
}
