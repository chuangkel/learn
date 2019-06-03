package com.github.chuangkel.java_learn.base.bases;

import java.io.InterruptedIOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

/**
 * @program: nevergiveup
 * @description:
 * @author: yaoqb23911
 * @create: 2018-12-04
 **/
public class Demo {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("IntegerReplacement_397", "cc", "bb");
        Collections.sort(list, (a, b) -> a.compareTo(b));
        System.out.println(list);
        InterruptedIOException i;
        Thread.interrupted();

        /**
         * 正则表达式：总结
         * . 表示任何字符
         * * 表示没有或者更多个前面的字符
         * ^ 表示开头
         * $ 表示结尾
         * ? 表示0个或者1个
         * + 表示至少一个或更多
         */
        Boolean b = Pattern.matches(".*01$","11101");
        System.out.println(b);

        List<String> list1 = new ArrayList<>();
        /** java8提供了接口的默认实现方法 */
        list1.sort((c,d)->{return 0;});


        Thread thread2 = new Thread(()->{});
        /** Runnable 既可以Thread启动，也可以线程池启动，Callable 只能线程池启动 */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(Demo::fetch);

        /** if获取的value值为空，或者不包含的key值，则返回默认值*/
        Map<Integer,String> map = new HashMap<>();
        map.getOrDefault(-1,"value");

    }

    public static Callable<String> fetch() {
        return () -> "Tricky example ;-)";
    }
}
