package com.github.chuangkel.java_learn.base.thread_synchronized;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**交替打印
 * foobar
 * foobar
 * foobar
 * foobar
 * foobar
 * @author xiezhang21010 [www.hundsun.com]
 * @date 2019-08-09 15:58
 */
public class ConditionFooBar {
    private ReentrantLock lock         = new ReentrantLock();
    private Condition     conditionFoo = lock.newCondition();
    private Condition     conditionBar = lock.newCondition();

    private CountDownLatch latch = new CountDownLatch(1);

    private boolean isFirst = true;

    private int n;

    public ConditionFooBar(int n) {
        this.n = n;
    }

    public void foo() {
        for (int i = 0; i < n; i++) {

            if (isFirst) {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            lock.lock();
            try {
                if (isFirst) {
                    System.out.print("foo");
                    conditionBar.signal();
                    isFirst = false;
                    continue;
                }
                conditionFoo.await();
                System.out.print("foo");
                conditionBar.signal();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void bar() {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                if (isFirst) {
                    latch.countDown();
                }
                conditionBar.await();
                System.out.println("bar");
                conditionFoo.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ConditionFooBar conditionFooBar = new ConditionFooBar(5);
        new Thread(() -> {
            conditionFooBar.foo();
        }).start();

        new Thread(() -> {
            conditionFooBar.bar();
        }).start();
    }
}
