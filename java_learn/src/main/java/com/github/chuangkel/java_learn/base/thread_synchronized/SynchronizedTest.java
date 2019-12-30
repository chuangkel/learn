package com.github.chuangkel.java_learn.base.thread_synchronized;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-11-25 09:21
 **/
public class SynchronizedTest {
    private static int num = 0;
    private static int THREAD_NUM = 80;

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 120, 1000L, TimeUnit.SECONDS
                , new ArrayBlockingQueue<Runnable>(THREAD_NUM), new PersonalThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


        CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_NUM, () -> {System.out.println("开始并发执行"); });
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);

        for (int i = 0; i < THREAD_NUM; i++) {
            executor.execute(() -> {
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //synchronized (SynchronizedTest.class) {
                    num += 1;
                //}
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        System.out.println(num);
    }

    private static class PersonalThreadFactory implements ThreadFactory {
        private ThreadGroup group = new ThreadGroup("personalT");
        private AtomicInteger threadId = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(group, r, "threadName-" + threadId.getAndIncrement());
        }
    }
}
