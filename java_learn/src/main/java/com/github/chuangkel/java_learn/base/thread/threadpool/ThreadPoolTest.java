package com.github.chuangkel.java_learn.base.thread.threadpool;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-16 09:39
 **/
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        /** 拒绝策略是在运行线程数即将大于线程池最大线程数情况下才发挥作用 */
        ThreadPoolExecutor poolExecutor  = new ThreadPoolExecutor(1, 2, 100L, TimeUnit.MILLISECONDS
                , new ArrayBlockingQueue<>(1), new DefaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
        poolExecutor.allowCoreThreadTimeOut(false);

        for(int i = 0; i < 2;i++){
            final int j = i;
            poolExecutor.submit(()->{
                System.out.println(j + "：getQueue().size()"+poolExecutor.getQueue().size());
                System.out.println(j + "：ActiveCount"+poolExecutor.getActiveCount());
                System.out.println(j + "：taskCount"+poolExecutor.getTaskCount());
                System.out.println(j + "：CompletedTaskCount"+ poolExecutor.getCompletedTaskCount());;
                for(int t = 0;t<10000000;t++){
                    t += 1;
                }
            });
        }
    }

    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}
