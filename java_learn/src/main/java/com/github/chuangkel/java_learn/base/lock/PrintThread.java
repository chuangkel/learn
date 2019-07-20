package main.java.com.github.chuangkel.java_learn.base.lock;/**
 * Created by Fortuner on 2019/7/19.
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-07-19 19:47
 **/
public class PrintThread implements Runnable {
    private int COUNT = 10;
    private ReentrantLock reentrantLock;
    private Condition conditionA;
    private Condition conditionB;
    private String c;
    PrintThread(ReentrantLock reentrantLock,Condition conditionA,Condition conditionB,String c){
        this.reentrantLock = reentrantLock;
        this.conditionA = conditionA;
        this.conditionB = conditionB;
        this.c = c;
    }
    @Override
    public void run() {
        try{
            reentrantLock.lock();
            for(int i = 0;i < COUNT; i++){
                System.out.print(c);
                try {
                    conditionA.await();
                    if(i < COUNT-1){
                        conditionB.signal();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            reentrantLock.unlock();
        }
    }
}
