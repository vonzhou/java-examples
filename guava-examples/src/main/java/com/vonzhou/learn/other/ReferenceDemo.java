package com.vonzhou.learn.other;

/**
 * @version 2017/2/10.
 */
public class ReferenceDemo {
    public static void main(String[] args) {
        String a = "xxx";
        StringBuilder sb = new StringBuilder();
        func(a, sb);
        System.out.println(a);
        System.out.println(sb.toString());
    }

    public static void func(String a, StringBuilder sb){
        a = "vonzhou";
        sb.append("vonzhou");
    }
}
