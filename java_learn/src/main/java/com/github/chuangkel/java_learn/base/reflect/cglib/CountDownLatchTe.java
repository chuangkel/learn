package com.github.chuangkel.java_learn.base.reflect.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

//import static org.junit.Assert.assertEquals;

/**
 * @program: javabase
 * @description: test类
 * @author: chuangkel
 * @create: 2019-02-03 17:33
 **/
public class CountDownLatchTe {
    public static void main(String[] args) {
        MethodIntercepterProxy m = new MethodIntercepterProxy();
        Do d = (Do)m.newIntancese(Do.class);
        d.doSomeThing();
        Field[] fields = Do.class.getFields();

        HashMap hashMap;
        try {
            PropertyDescriptor p = new PropertyDescriptor(fields[0].getName(),Do.class);
            Object ob = p.getWriteMethod().invoke(new Do(),null);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Do.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                return method.invoke(o,objects);
            }
        });
        Do d = (Do) enhancer.create();

        d.doSomeThing();

    }
    @Test
    public void testcopy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Do.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return "Hello cglib!";
                } else {
                    throw new RuntimeException("Do not know what to do.");
                }
            }
        });
        Do proxy = (Do) enhancer.create();
//        assertEquals("Hello cglib!", proxy.test(null));
//        assertNotEquals("Hello cglib!", proxy.toString());
    }

}
