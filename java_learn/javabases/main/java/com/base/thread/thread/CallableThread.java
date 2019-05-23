package com.base.thread.thread;

import java.util.concurrent.Callable;

/**
 * @program: javaNewCharacteristic
 * @description: callable 线程回调
 * @author: yaoqb23911
 * @create: 2018-12-28
 **/
public class CallableThread implements Callable {
    @Override
    public Object call() throws Exception {

        return "callable";
    }
}
