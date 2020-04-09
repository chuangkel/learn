package com.github.chuangkel.java_learn.base.PhantomReference;

import org.omg.SendingContext.RunTime;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-03-16 14:07
 **/
public class PhantomReferenceDmeo {

    private static final ReferenceQueue<Object> queue = new ReferenceQueue<>();

    public static void main(String[] args) throws InterruptedException {
        Person person = new Person("小明");
        Animal animal = new Animal(person,"animal");

        person = null;

        System.gc();
        //RunTime.getRunTime.gc();

        Thread.sleep(1000L);

        printReferenceQueue();
    }

    private static void printReferenceQueue(){
        int i = 0;Object obj = null;
        while ((obj = queue.poll()) !=null){
            i ++;
        }
        System.out.println("队列长度i："+i);
    }
    static class Animal extends PhantomReference<Object> {
        private String name;

        public Animal(Person person, String name) {
            super(person, queue);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
