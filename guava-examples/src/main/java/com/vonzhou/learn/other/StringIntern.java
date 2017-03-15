package com.vonzhou.learn.other;

/**
 * @version 2017/1/20.
 */
public class StringIntern {
    public static void main(String[] args) {
        String s = new String("1");
        String s2 = "1";
        s.intern();
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        String s4 = "11";
        s3.intern();
        System.out.println(s3 == s4);
    }
}
