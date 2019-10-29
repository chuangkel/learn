package com.github.chuangkel.java_learn.pattern1.structure.Obervers;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-10-19 10:01
 **/
public class ConcreteObserver implements Observer {
    private Subject subject;

    public ConcreteObserver(Subject subject) {
        this.subject = subject;
        subject.registObserver(this);
    }

    @Override
    public void update(String msg) {
        System.out.println(this.toString()+" 收到通知："+msg);
    }
}
