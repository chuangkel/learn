package com.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-02-08 18:48
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ATest {
    /**
     * 参数名value 参数类型String
     * @return
     */
    String value() default "default value";
}
