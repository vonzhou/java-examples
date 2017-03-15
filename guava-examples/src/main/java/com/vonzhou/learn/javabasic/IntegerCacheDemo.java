package com.vonzhou.learn.javabasic;

/**
 * @version 2017/2/21.
 */
public class IntegerCacheDemo {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Integer a = 1; // 等价于 Integer a = valueOf(1)
        Integer b = 1;
        System.out.println(a == b); // true

        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d); // false

        System.out.println(c.equals(d)); // true
    }

}
