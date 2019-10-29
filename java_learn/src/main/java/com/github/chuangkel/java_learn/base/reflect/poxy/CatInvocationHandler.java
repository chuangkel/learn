package com.github.chuangkel.java_learn.base.reflect.poxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-02-03 15:32
 **/
public class CatInvocationHandler implements InvocationHandler {
    private Object object;

    CatInvocationHandler(Object object) {
        this.object = object;
    }

    public Object newProxyInstance() {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用eat()之前");
        Object result =  method.invoke(object, args);

        System.out.println("调用eat()之后");
        return result;
    }
}
