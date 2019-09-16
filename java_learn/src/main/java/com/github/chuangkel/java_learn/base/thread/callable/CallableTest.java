package com.github.chuangkel.java_learn.base.thread.callable;

import java.util.concurrent.*;

/**
 * @program: javaNewCharacteristic
 * @description: main
 * @author: yaoqb23911
 * @create: 2018-12-28
 **/
public class CallableTest {
    ThreadPoolExecutor threadpool = new ThreadPoolExecutor(1, 1, 100L,
            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1), new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableThread c = new CallableThread();
        //Callable需要包装成FutureTask
        FutureTask futureTask = new FutureTask(c);
        Thread t = new Thread(futureTask);
        t.start();
        String result = (String) futureTask.get();
        System.out.println(result);
    }
}
