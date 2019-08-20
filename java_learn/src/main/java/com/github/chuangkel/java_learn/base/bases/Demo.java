package com.github.chuangkel.java_learn.base.bases;

import java.io.InterruptedIOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.IntPredicate;
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
         * + 表示至少一个或更多
         * ^ 表示开头
         * $ 表示结尾
         * ? 表示0个或者1个
         *
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
        //第二个参数 方法引用
        forEach(
                Arrays.asList(1,2,3,4,5),
                (Integer ii) -> System.out.println(ii)
        );
        //默认函数的参数是int
        IntPredicate intPredicate = (t)->{return t>0;};

        Object object = new Object();
        System.out.println("object:"+object.hashCode());
    }
    public static <T> void forEach(List<T> list, Consumer<T> c){
        for(T i: list){
            c.accept(i);
        }
    }

    public static Callable<String> fetch() {
        return () -> "Tricky example ;-)";
    }


    /**
     * 判断对象的参数是否是null
     * @param object
     * @return
     */
    private boolean paramIsNull(Object object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                if (value == null) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    //public long update() {
    //    for(;;) {
    //        long version = this.version;
    //        long newVersion = version + 1;
    //        if (UNSAFE.compareAndSwapLong(this, VERSION_OFFSET, version, newVersion)) {
    //            return newVersion;
    //        }
    //    }
    //}
}
