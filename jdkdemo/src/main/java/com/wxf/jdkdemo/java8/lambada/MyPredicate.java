package com.wxf.jdkdemo.java8.lambada;

@FunctionalInterface
public interface MyPredicate<T> {
    public boolean test(T t);
}
