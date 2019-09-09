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
//public class SimpleLocalCache<T> {
//
//    protected final static Logger LOG = LoggerFactory.getLogger(LoggerFileNames.CACHE_DIGEST);
//
//    private final Memoizer<Object, TimestampCacheModel<T>> cacheMap = new Memoizer<Object, TimestampCacheModel<T>>();
//
//    private final long                                     expired;
//
//    /**
//     * @param expired
//     */
//    public SimpleLocalCache(long expired) {
//        this.expired = expired;
//    }
//
//    public T loadFromCache(final Object cachekey, final CacheStrategyEnum cacheStrategy,
//                           final LazyLoader<T> lazyLoader) {
//
//        Computable<Object, TimestampCacheModel<T>> computable = new Computable<Object, TimestampCacheModel<T>>() {
//
//            @Override
//            public TimestampCacheModel<T> compute(Object arg) throws InterruptedException {
//                T needCacheData = lazyLoader.load();
//
//                if (needCacheData == null && CacheStrategyEnum.CACHE_NOT_NULL == cacheStrategy) {
//                    return null;
//                }
//                if (needCacheData == null && CacheStrategyEnum.CACHE_NULL == cacheStrategy) {
//                    return new TimestampCacheModel<T>(null, expired);
//                }
//
//                return new TimestampCacheModel<T>(needCacheData, expired);
//            }
//        };
//
//        TimestampCacheModel<T> cacheInfo = cacheMap.initOnceAndGet(cachekey, computable);
//
//        if (cacheInfo == null) {
//            LoggerUtil.debug(LOG, "缓存SimpleLocalCache类loadFromCache方法,返回结果为空", cacheInfo);
//            return lazyLoader.load();
//        }
//
//        if (isExpired(cacheInfo)) {
//            LoggerUtil.debug(LOG, "缓存SimpleLocalCache类loadFromCache方法,过期", cacheInfo);
//            cacheMap.remove(cachekey);
//            cacheInfo = cacheMap.initOnceAndGet(cachekey, computable);
//
//            if (cacheInfo == null) {
//                LoggerUtil.debug(LOG, "缓存SimpleLocalCache类loadFromCache方法,过期后为空", cacheInfo);
//                return lazyLoader.load();
//            }
//        }
//
//        return cacheInfo.getCacheObject();
//    }
//
//    public boolean isExpired(TimestampCacheModel<T> cacheInfo) {
//        if (cacheInfo == null) {
//            return false;
//        }
//        long expiredTimestamp = cacheInfo.getExpiredTimestamp();
//        if (expiredTimestamp <= 0) {
//            return false;
//        }
//
//        return System.currentTimeMillis() > expiredTimestamp;
//    }
//
//    public void cacheMapClear() {
//        cacheMap.clear();
//    }
//
//    public void cacheKeyClear(String key) {
//        cacheMap.remove(key);
//    }
//
//    public String getAllCacheKey() {
//        return cacheMap.getAllCacheKey();
//    }
//}
