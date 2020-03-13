package com.github.chuangkel.java_learn.base.thread;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-12-03 19:54
 **/
public class PossibleReOrdering {

    static int a = 0, b = 0;
    static int x, y;
    public static void main(String[] args) throws InterruptedException {
        System.gc();
        for(int j = 0; ;j++){

            Thread t1 = new Thread(()->{
                for(int i = 0;i < 100;i ++){
                    a = 1;
                    x = a;
                }
            });
            t1.start();

            Thread t2 = new Thread(()->{
                for(int i = 0;i < 100;i ++){
                    b = 1;
                    y = b;
                }
            });

            t2.start();
            t1.join();
            t2.join();

            if(x == 0 && y == 0) {
                System.out.println("总共循环次数"+j);
                break;
            }
        }
    }

}
