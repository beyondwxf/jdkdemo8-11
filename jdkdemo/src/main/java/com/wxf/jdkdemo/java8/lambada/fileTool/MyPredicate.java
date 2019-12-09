package com.wxf.jdkdemo.java8.lambada.fileTool;

@FunctionalInterface
public interface MyPredicate<T> {
    public boolean test(T t);
}
