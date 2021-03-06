package com.github.chuangkel.java_learn.base.collection.clone;

/**
 * @program: javaNewCharacteristic
 * @description: 克隆的对象
 * @author: chuangkel
 * @create: 2019-01-27 08:41
 **/
public class CloneFather implements Cloneable {
    /**
     * 浅复制：A对象中包含了对象B,复制对象A,B对象不会被复制，会将B对象的引用复制。
     * 深复制：对象里包含的对象都复制。
     */
    private CloneChild child = new CloneChild("a");
    private CloneChild[] children = {new CloneChild("a")};

    @Override
    protected Object clone() {
        CloneFather father = null;
        try {
            father = (CloneFather) super.clone();
            father.child = (CloneChild) this.child.clone();
            father.children = children.clone();
            for (int i = 0; i < children.length; i++) {
                father.children[i] = (CloneChild) this.children[i].clone();
            }
            return father;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        CloneFather fa = new CloneFather();
        CloneFather fa1 = (CloneFather) fa.clone();

        System.out.println("父对象是否相等：" + (fa == fa1));
        System.out.println("子对象是否相等：" + (fa.child == fa1.child));
        //System.out.println("子对象hash：" + (fa.child.hashCode()) + " " + (fa1.child.hashCode()));
        System.out.println("数组是否相等：" + (fa.children == fa1.children));
        System.out.println("数组里面的元素是否相等：" + (fa.children[0] == fa1.children[0]));
    }
}
