package com.vonzhou.learn.java;

/**
 * @version 2016/9/3.
 */
public class StringBuilderAndStringBuffer {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("hello");



        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("hello");

        System.out.println(sb);
        System.out.println(sb.toString());
        System.out.println(stringBuffer);
        System.out.println(stringBuffer.toString());
    }
}
