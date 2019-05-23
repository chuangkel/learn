package com.github.springboot.myannotation;

import org.junit.Test;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-04-12 14:07
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ContextConfiguration
public @interface MyTest {

    @AliasFor(annotation = Test.class)
    @Test
    String mytest();
}
