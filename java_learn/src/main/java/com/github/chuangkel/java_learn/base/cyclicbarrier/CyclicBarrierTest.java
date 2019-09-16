package com.github.chuangkel.java_learn.base.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class CyclicBarrierTest{
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        for(int i = 0; i < 20;i++){// 最大为10 的倍数，可以反复使用CyclicBarrier
            String str = "aaa";
            final int a = i;
            new Thread(()->{
                try {
                    System.out.println(str);
                    System.out.println(a);
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}