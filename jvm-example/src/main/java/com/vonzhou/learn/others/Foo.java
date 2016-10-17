package com.vonzhou.learn.others;

/**
 * @version 2016/10/17.
 */
public class Foo {
    public static void f1(){
        f2();
        System.out.println("f1");
    }

    public static void f2(){
        return;
    }

    public static void main(String[] args) {
        f1();
    }
}
