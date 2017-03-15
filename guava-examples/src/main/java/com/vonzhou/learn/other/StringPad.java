package com.vonzhou.learn.other;

import org.apache.commons.lang3.StringUtils;

/**
 * @version 2016/12/15.
 */
public class StringPad {
    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }

    public static void main(String args[]) throws Exception {
        System.out.println(padRight("Howto", 20) + "*");
        System.out.println(padLeft("Howto", 20) + "*");



        String s = "xxxxbccccdcc.so";
        String after = StringUtils.substringAfterLast(s, "/");
        if(StringUtils.isBlank(after))
            after = s;
        System.out.println("after = " + after);
        String btw = StringUtils.substringBefore(after, ".so");
        System.out.println(btw + "-------------");
        System.out.println(StringUtils.isBlank(btw));
    }
}
