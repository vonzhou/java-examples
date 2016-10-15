package com.vonzhou.learn.guava.basics;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于 Comparator, 提供了很多便利的实例方法
 *
 * @see `https://github.com/google/guava/wiki/OrderingExplained`
 * Created by vonzhou on 16/8/21.
 */
public class OrderingDemo {
    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    public static void test1() {
        Ordering ordering = Ordering.natural();
        int res = ordering.compare("abc", "ab");
        System.out.println(res);
        Bar f1 = new Bar("fz", "city");
        Bar f2 = new Bar("fz", "cit");
        System.out.println(f1);
        System.out.println(f2);
        res = Ordering.usingToString().compare(f1, f2);   // 不太适用, 因为toString 里面含有其他字符串
        System.out.println(res);
    }

    public static void test2() {
        List<String> names = new ArrayList<String>();
        names.add("123");
        names.add("abcd");
        names.add("abc");
        names.add("abc");
        names.add("abb");

        List<String> top3 = Ordering.natural().greatestOf(names, 3);
        System.out.println(top3);
    }

    public static void test3() {
        Ordering<String> byLengthOrdering = new Ordering<String>() {
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };
        String min = byLengthOrdering.min("abc", "");
        System.out.println("min:" + min);
    }
}

class Bar {
    private String name;
    private String city;

    public Bar(String name, String city) {
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString() {
        return "name:" + name + ",city:" + city;
    }
}
