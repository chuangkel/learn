package main.java.com.github.chuangkel.java_learn.base.lock;/**
 * Created by Fortuner on 2019/7/19.
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-07-19 19:45
 **/
public class ReentranLockTest {

    static ReentrantLock reentrantLock = new ReentrantLock();
    static Condition conditionA = reentrantLock.newCondition();
    static Condition conditionB = reentrantLock.newCondition();
    public static void main(String[] args) {
        PrintThread printThreadA = new PrintThread(reentrantLock,conditionA,conditionB,"A");
        PrintThread printThreadB = new PrintThread(reentrantLock,conditionB,conditionA,"B");
        Thread tA = new Thread(printThreadA);
        Thread tB = new Thread(printThreadB);
        tA.start();
        tB.start();

    }
}
