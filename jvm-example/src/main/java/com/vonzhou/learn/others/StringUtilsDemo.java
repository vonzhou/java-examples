package com.vonzhou.learn.others;

import org.apache.commons.lang3.StringUtils;

/**
 * @version 2016/10/19.
 */
public class StringUtilsDemo {
    static class MyObject{
        private int like;
        private long time;
    }

    public static void main(String[] args) {
        String s = StringUtils.stripStart("&nbsp; &nbsp;asfda &nbsp; &nbsp;", "&nbsp; ");
        s = StringUtils.stripEnd(s, "&nbsp; ");
        System.out.println(s);
    }
}
