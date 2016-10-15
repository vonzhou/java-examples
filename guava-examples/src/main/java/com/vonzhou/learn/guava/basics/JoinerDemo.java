package com.vonzhou.learn.guava.basics;

import com.google.common.base.Joiner;

import java.util.Arrays;
import java.util.List;

/**
 * @version 2016/10/8.
 */
public class JoinerDemo {
    public static void test1() {
        Joiner joiner = Joiner.on(";").skipNulls();
        System.out.println(joiner.join("Hello", "Vonzhou", null, "ABC"));
    }

    /**
     * null是最后一个元素时， 导致NullPointerException
     * 原因是 iterable方法中会把第三个参数作为后续可变参数数组，为空的话直接抛出异常，所以这个地方有点奇妙
     * 对于 useForNull() 同样如此
     */
    public static void test3() {
        Joiner joiner = Joiner.on(";").skipNulls();
        System.out.println(joiner.join("Hello", "Vonzhou", null));
    }

    public static void test4() {
        Joiner joiner = Joiner.on(";").useForNull("#");
        System.out.println(joiner.join("Hello", "Vonzhou", null));
    }

    /**
     * join 集合类不存在上述的问题
     */
    public static void test5() {
        List<String> list = Arrays.asList("A", "B", null);
        Joiner joiner = Joiner.on(";").skipNulls();
        System.out.println(joiner.join(list));
    }

    /**
     * 开头会多输出一个 “;”
     */
    public static void test6() {
        StringBuilder sb = new StringBuilder();
        Joiner joiner = Joiner.on(";").skipNulls();
        System.out.println(joiner.join(sb, "A", "B"));
    }

    public static void test2() {
        Joiner joiner = Joiner.on(";");
        joiner.skipNulls();
        System.out.println(joiner.join("Hello", "Vonzhou", null, "ABC"));
    }

    public static void main(String[] args) {
        test6();
    }
}
