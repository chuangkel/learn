package com.github.chuangkel.java_learn.base.generic;

/**
 * @program: learn
 * @description: 泛型实践
 * @author: chuangkel
 * @create: 2019-10-21 16:18
 **/
public class GenericDemo<S,T extends S> {
    public <T,S extends T> T get(S s){
        T t = null;
        return t;
    }


}
