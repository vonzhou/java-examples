package com.vonzhou.learn.spring.use;

import org.springframework.util.Assert;

/**
 * @version 2016/9/17.
 */
public class AssertDemo {
    public static void main(String[] args) {
        String s = "";
        /**  java.lang.IllegalArgumentException */
        Assert.hasText(s, "s Cannot Be Empty");
        System.out.println("Get Here----------");
    }
}
