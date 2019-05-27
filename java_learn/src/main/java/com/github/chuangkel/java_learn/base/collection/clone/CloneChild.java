package com.github.chuangkel.java_learn.base.collection.clone;

/**
 * @program: javaNewCharacteristic
 * @description: 深复制
 * @author: chuangkel
 * @create: 2019-01-27 08:30
 **/
public class CloneChild implements Cloneable {

    private String field;


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }


    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
