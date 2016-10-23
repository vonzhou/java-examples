package com.vonzhou.learn.jvm.classloading;

/**
 * @version 2016/10/21.
 */
public class ConstClass {
    static {
        System.out.println("ConstClass init....");
    }
    public static final String CONST = "hello world...";
}
