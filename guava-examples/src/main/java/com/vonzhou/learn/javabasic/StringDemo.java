package com.vonzhou.learn.javabasic;

import org.apache.commons.lang3.StringUtils;

/**
 * @version 2017/2/21.
 */
public class StringDemo {
    public static void main(String[] args) {
//        String s = "a,b,,";
//        String[] arr = s.split(",");
//        System.out.println(arr.length);
        String s = "xxxxxxxxx";
        System.out.println(StringUtils.substring(s, 0, 2));
    }
}
