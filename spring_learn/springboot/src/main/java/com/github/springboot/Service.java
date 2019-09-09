package com.github.springboot;

import com.github.springboot.myannotation.MyAnnotation;
import org.junit.Test;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-04-12 13:30
 **/
@MyAnnotation(value = "aa") //别名传入的值须一致，或者只传入一个
public class Service {

    @Test
    public void test() {

//        MyAnnotation myAnnotation = getClass().getAnnotation(MyAnnotation.class);
        //@AliasFor 注解需要Spring 的AnnotationUtils工具类处理
        MyAnnotation myAnnotation = AnnotationUtils.findAnnotation(getClass(),MyAnnotation.class);
        System.out.println(myAnnotation.location());
        System.out.println(myAnnotation.value());

    }
}
