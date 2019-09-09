///**
// * Alipay.com Inc.
// * Copyright (c) 2004-2015 All Rights Reserved.
// */
//package com.github.chuangkel.java_learn.base.cache;
//
//import com.alipay.tacaccountcore.facade.utils.log.LoggerFileNames;
//import com.alipay.tacaccountcore.facade.utils.log.LoggerUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.concurrent.Callable;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.Future;
//import java.util.concurrent.FutureTask;
//
//public class Memoizer<A, V> {
//    private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
//
//    protected final static Logger                 LOG   = LoggerFactory.getLogger(LoggerFileNames.CACHE_DIGEST);
//
//
//    public V initOnceAndGet(final A key, final Computable<A, V> computable) {
//        V result = null;
//        Future<V> f = cache.get(key);
//
//        LoggerUtil.debug(LOG, "缓存Memoizer类initOnceAndGet方法,key为{0},返回结果f为{1}", key, f);
//        if (f == null) {
//            //这里new  computable
//            Callable<V> eval = new Callable<V>() {
//                /**
//                 * @see Callable#call()
//                 */
//                @Override
//                public V call() throws InterruptedException {
//                    V computeResult = computable.compute(key);
//                    if(computeResult != null && ((TimestampCacheModel)computeResult).getCacheObject() != null) {
//                        LoggerUtil.info(LOG,"缓存Memoizer类callable执行结果,key为{0},返回结果result为{1}",key,((TimestampCacheModel)computeResult).getCacheObject().toString());
//                    }else {
//                        LoggerUtil.info(LOG,"缓存Memoizer类callable执行结果,key为{0},返回结果result为null",key);
//                    }
//                    return computeResult;
//                }
//            };
//            FutureTask<V> ft = new FutureTask<V>(eval);
//            f = cache.putIfAbsent(key, ft);//第一次put(key)时返回null
//            if (f == null) {
//                f = ft;
//                ft.run();
//            }
//        }
//        try {
//            result = f.get();
//            LoggerUtil.debug(LOG,"缓存Memoizer类initOnceAndGet方法,key为{0},返回结果result为{1}",key,result);
//            return result;
//        } catch (Throwable e) {
//            LoggerUtil.error(e,LOG,"缓存Memoizer类initOnceAndGet方法异常,key为{0},返回结果result为{1}",key,result);
//            cache.remove(key);
//            throw new RuntimeException(e);
//        } finally {
//
//            if (result == null) {
//                cache.remove(key, f);
//            }
//        }
//    }
//
//    /**
//     *
//     * @param key
//     * @return
//     */
//    public V remove(A key) {
//        Future<V> f = cache.remove(key);
//        V result = null;
//        try {
//            if (f != null) {
//                result = f.get();
//                return result;
//            }
//        } catch (Exception e) {
//            LoggerUtil.error(e,LOG,"缓存Memoizer类remove方法异常,key为{0},返回结果result为{1}",key,result);
//        }
//        return null;
//    }
//
//    public void clear() {
//        cache.clear();
//    }
//
//    public String getAllCacheKey() {
//        if (null != cache.keySet() && cache.keySet().size() != 0) {
//            return cache.keySet().toString();
//        } else {
//            return null;
//        }
//    }
//}
