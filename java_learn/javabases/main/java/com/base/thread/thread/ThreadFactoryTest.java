package com.base.thread.thread;

import java.util.concurrent.ThreadFactory;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-02-28 11:31
 **/
public class ThreadFactoryTest implements ThreadFactory {
    /**
     * 自定义线程工厂 DefaultThreadFactory 是默认的线程工厂  可以参考其实现
     * @param r
     * @return
     */
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r);
    }
}
