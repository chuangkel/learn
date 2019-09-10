package com.github.chuangkel.java_learn.base.thread_synchronized;

import com.github.chuangkel.java_learn.base.lock.SynchronizedTest;

/**
 * @program: learn
 * @description: 因为常量池的存在，如果多个地方使用了同一个字符串作为锁（而本意不是这样的话），就会造成额外的阻塞。
 * @author: chuangkel
 * @create: 2019-09-10 18:56
 **/
public class SynchronizedObj {
    static  String s = "1";
    public void showC(){
        synchronized (s){
            System.out.println("showC");
            while (true){
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedObj synchronizedTest = new SynchronizedObj();
        new Thread(()->{
            synchronizedTest.showC();
        }).start();
        new Thread(()->{
            synchronized("1"){
                System.out.println("1");
            }
        }).start();
    }
}
//String s = "1";
//String s1 = new String("1");
//System.out.println(s == s1);//false