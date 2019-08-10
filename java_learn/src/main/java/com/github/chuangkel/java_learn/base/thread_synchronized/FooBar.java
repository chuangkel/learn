package com.github.chuangkel.java_learn.base.thread_synchronized;

/**
 * 交替打印
 *  * foobar
 *  * foobar
 *  * foobar
 *  * foobar
 *  * foobar
 * @author xiezhang21010 [www.hundsun.com]
 * @date 2019-08-05 20:06
 */
public class FooBar {

    private int n = 0;

    private boolean firstFoo = true;

    private boolean fooFinish = false;

    private boolean barFinish = false;

    FooBar(int n) {
        this.n = n;
    }

    public void foo() throws InterruptedException {

        for (int i = 0; i < n; i++) {
            //
            synchronized (this) {
                if (firstFoo) {
                    System.out.print("foo");
                    fooFinish = true;
                    barFinish = false;
                    firstFoo = false;
                    this.notify();
                } else {
                    if (!barFinish) {
                        this.wait();
                    }
                    System.out.print("foo");
                    fooFinish = true;
                    barFinish = false;
                    this.notify();
                }
            }

        }
    }

    public void bar() throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (this) {
                if (!fooFinish) {
                    this.wait();
                }
                System.out.println("bar");
                barFinish = true;
                fooFinish = false;
                this.notify();
            }
        }
    }
}
