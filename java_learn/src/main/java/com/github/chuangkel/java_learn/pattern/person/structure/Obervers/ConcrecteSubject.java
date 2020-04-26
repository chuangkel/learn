package com.github.chuangkel.java_learn.pattern.person.structure.Obervers;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-10-19 10:02
 **/
public class ConcrecteSubject implements Subject {
    @Override
    public void registObserver(Observer observer) {
        list.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        list.remove(observer);
    }
    @Override
    public void notifyObserver() {
        if(changed()) {
            for (Observer observer : list) {
                observer.update("主题消息");
            }
        }
    }
    private boolean changed() {
        //达到某种条件
        return true;
    }
}
