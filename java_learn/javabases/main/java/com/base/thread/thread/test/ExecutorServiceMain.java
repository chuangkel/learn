package com.base.thread.thread.test;


import java.util.concurrent.*;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-02-18 15:13
 **/
public class ExecutorServiceMain {

    public static void main(String[] args) {
        //创建工作线程数量为1个的线程池
        ExecutorService pool1 = Executors.newSingleThreadExecutor();
        //创建无限制线程数的线程池
        ExecutorService pool2 = Executors.newCachedThreadPool();
        //创建固定数量的线程池
        ExecutorService pool3 = Executors.newFixedThreadPool(3);
        //支持定时、周期性任务的执行
        ExecutorService pool4 = Executors.newScheduledThreadPool(1);

        ThreadPoolExecutor ps = new ThreadPoolExecutor(2,4,60L,TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),new ThreadPoolExecutor.DiscardPolicy());
//        new ThreadPoolExecutor.CallerRunsPolicy() 重试，自动重复调用excute()
//        new ThreadPoolExecutor.AbortPolicy() 直接拒绝，抛出异常
//        new ThreadPoolExecutor.DiscardOldestPolicy() 抛弃队列头部的（即等待时间最久的）
//        new ThreadPoolExecutor.DiscardPolicy() 直接拒绝，不抛出异常
        //ArrayBlockingQueue 固定数量线程
        //DelayedWorkQueue
        //LinkedBlockingDeque
        //SynchronousQueue 无限制数量线程
        for(int i = 0; i < 16; i ++){
//            try {
//                //停顿
//                Thread.sleep(1L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            ps.submit(new Task(String.valueOf(i)));
        }
        ps.shutdown();
    }

    static class Task implements Callable{
        String flag;
        Task(String flag){
            this.flag = flag;
        }
        @Override
        public Object call() throws Exception {
            System.out.println(flag);
            return flag;
        }
    }

}
