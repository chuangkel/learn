package com.github.chuangkel.java_learn.pattern1.structure.Obervers;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-10-19 10:12
 **/
public class Main {
    public static void main(String[] args) {
        ConcrecteSubject subject = new ConcrecteSubject();

        ConcreteObserver concreteObserver = new ConcreteObserver(subject);
        ConcreteObserver concreteObserver1 = new ConcreteObserver(subject);

        subject.notifyObserver();
    }
}
