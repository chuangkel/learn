package com.github.chuangkel.java_learn.base.singleModle;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-09 16:59
 **/
public class StaticSingleTest {

    static class StaticSingle{
        private static String field;

        public static String getField() {
            return field;
        }

        public static void setField(String field) {
            StaticSingle.field = field;
        }
    }
}
