package com.vonzhou.learn.jvm.classloading;

/**
 * @version 2016/10/21.
 */
public class SuperClass {
    static {
        System.out.println("SuperClass init....");
    }
    public static final int VALUE = 123;
}
