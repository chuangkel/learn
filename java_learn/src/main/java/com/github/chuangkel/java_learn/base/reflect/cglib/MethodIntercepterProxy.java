//package com.github.chuangkel.java_learn.base.reflect.cglib;
//
//import net.sf.cglib.proxy.Enhancer;
//import net.sf.cglib.proxy.MethodInterceptor;
//import net.sf.cglib.proxy.MethodProxy;
//
//import java.lang.reflect.Method;
//
///**
// * @program: javabase
// * @description: cglib实现动态代理，效率有所提升
// * @author: chuangkel
// * @create: 2019-02-03 17:26
// **/
//public class MethodIntercepterProxy implements MethodInterceptor {
//
//    private Enhancer enhancer = new Enhancer();
//
//    @Override
//    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//        System.out.println("cglib正在调用");
//        Object o1 = methodProxy.invokeSuper(o,objects);
////       Object o1 = methodProxy.invoke(o,objects);
//        return o1;
//    }
//
//    public Object newIntancese(Class c){
//        enhancer.setSuperclass(c);
//        enhancer.setCallback(this);
//        //生成代理对象
//        /**
//         * 不能代理类中类
//         */
//        return enhancer.create();
//    }
//}
