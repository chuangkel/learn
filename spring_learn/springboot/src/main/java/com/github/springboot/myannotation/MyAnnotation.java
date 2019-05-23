package com.github.springboot.myannotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: javabase
 * @description:
 * @author: yaoqb23911
 * @create: 2019-04-12
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface MyAnnotation {

    @AliasFor("location")
    String value() default "" ;
    @AliasFor("value")
    String location() default "";
}
