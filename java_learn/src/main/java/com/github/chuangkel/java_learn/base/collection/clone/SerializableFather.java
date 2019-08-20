package com.github.chuangkel.java_learn.base.collection.clone;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.springframework.util.SerializationUtils;

import java.io.*;

/**
 * @program: javaNewCharacteristic
 * @description: 克隆的对象
 * @author: chuangkel
 * @create: 2019-01-27 08:41
 **/
public class SerializableFather implements Cloneable, Serializable {
    /**
     * 浅复制：A对象中包含了对象B,复制对象A,B对象不会被复制，会将B对象的引用复制。
     * 深复制：对象里包含的对象都复制。
     */
    private CloneChild child = new CloneChild("a");
    private CloneChild[] children = {new CloneChild("a")};


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        SerializableFather fa = new SerializableFather();
        SerializableFather cloneFa = SerializableCloneUtil.clone(fa);
        System.out.println("序列化深拷贝对象：");
        System.out.println("父对象是否相等：" + (fa == cloneFa));
        System.out.println("子对象是否相等：" + (fa.child == cloneFa.child));
        //System.out.println("子对象hash：" + (fa.child.hashCode()) + " " + (fa1.child.hashCode()));
        System.out.println("数组是否相等：" + (fa.children == cloneFa.children));
        System.out.println("数组里面的元素是否相等：" + (fa.children[0] == cloneFa.children[0]));
    }
}
