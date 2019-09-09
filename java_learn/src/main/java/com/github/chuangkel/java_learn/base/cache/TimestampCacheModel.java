/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.github.chuangkel.java_learn.base.cache;

public class TimestampCacheModel<T> {

    private T    cacheObject;

    private long expiredTimestamp;

    public TimestampCacheModel(T cacheObject, long expiredMillsecond) {
        super();
        this.cacheObject = cacheObject;
        this.expiredTimestamp = expiredMillsecond <= 0 ? -1 : System.currentTimeMillis()
                                                              + expiredMillsecond;
    }

    /**
     * Getter method for property <tt>cacheObject</tt>.
     * 
     * @return property value of cacheObject
     */
    public T getCacheObject() {
        return cacheObject;
    }

    /**
     * Setter method for property <tt>cacheObject</tt>.
     * 
     * @param cacheObject value to be assigned to property cacheObject
     */
    public void setCacheObject(T cacheObject) {
        this.cacheObject = cacheObject;
    }

    /**
     * Getter method for property <tt>expiredTimestamp</tt>.
     * 
     * @return property value of expiredTimestamp
     */
    public long getExpiredTimestamp() {
        return expiredTimestamp;
    }

    /**
     * Setter method for property <tt>expiredTimestamp</tt>.
     * 
     * @param expiredTimestamp value to be assigned to property expiredTimestamp
     */
    public void setExpiredTimestamp(long expiredTimestamp) {
        this.expiredTimestamp = expiredTimestamp;
    }

}
