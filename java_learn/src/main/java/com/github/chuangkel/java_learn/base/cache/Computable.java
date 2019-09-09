package com.github.chuangkel.java_learn.base.cache;

public interface Computable<A, V> {

    V compute(A arg) throws InterruptedException;
}
