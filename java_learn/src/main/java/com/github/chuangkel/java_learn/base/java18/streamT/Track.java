package com.github.chuangkel.java_learn.base.java18.streamT;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-05-30 15:42
 **/
public class Track {

    private int length;

    private String name ;

    public Track(String name,int length) {
        this.length = length;
        this.name = name;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
