package com.github.chuangkel.java_learn.base.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

class OffHeapArray {
    // 一个int等于4个字节
    private static final int INT = 4;
    private long size;
    private long address;

    private static Unsafe unsafe;
    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // 构造方法，分配内存
    public OffHeapArray(long size) {
        this.size = size;
        // 参数字节数
        address = unsafe.allocateMemory(size * INT);
    }
    
    // 获取指定索引处的元素
    public int get(long i) {
        return unsafe.getInt(address + i * INT);
    }
    // 设置指定索引处的元素
    public void set(long i, int value) {
        unsafe.putInt(address + i * INT, value);
    }
    // 元素个数
    public long size() {
        return size;
    }
    // 释放堆外内存
    public void freeMemory() {
        unsafe.freeMemory(address);
    }
}
