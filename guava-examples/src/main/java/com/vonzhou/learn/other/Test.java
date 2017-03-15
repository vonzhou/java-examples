package com.vonzhou.learn.other;

import org.apache.log4j.Logger;

/**
 * @version 2016/12/13.
 */
public class Test {
    public static Logger logger = Logger.getLogger(Test.class);

    public static void main(String[] args) {
        String s = "#00  pc   /a/b/c.so";
        System.out.println(s.trim().startsWith("#00  pc "));
        s = s.substring(s.indexOf("/"), s.indexOf(".so")) + ".so";
        System.out.println(s);
    }
}
