package com.vonzhou.learn.jvm.classloading;

/**
 * @version 2016/10/21.
 */
public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init...");
    }
}
