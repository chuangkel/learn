package com.github.chuangkel.java_learn.pattern.person.structure.Obervers;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: learn
 * @description: 主题 通知观察者
 * @author: chuangkel
 * @create: 2019-10-19
 **/
public interface Subject {
    List<Observer> list = new ArrayList<>();
    void registObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver();
}
