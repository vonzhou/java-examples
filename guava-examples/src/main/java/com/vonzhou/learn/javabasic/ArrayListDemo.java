package com.vonzhou.learn.javabasic;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 2017/2/22.
 */
public class ArrayListDemo {
    public static void main(String[] args) {
//        test3();
        test4();
    }

    public static void test1() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        List<String> sub = list.subList(0, 1);

        // java.lang.ClassCastException: java.util.ArrayList$SubList cannot be cast to
        // java.util.ArrayList
        ArrayList<String> force = (ArrayList<String>) sub;
    }

    public static void test2() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        List<String> sub = list.subList(0, 2); // 是一个视图

        sub.set(0, "xxxxxxx"); // 对subList的修改会反应在原ArrayList上
        System.out.println(list); // [xxxxxxx, 2, 3]

        sub.remove(0);
        System.out.println(sub.get(0));
        System.out.println(list); // [2, 3]

    }

    public static void test3() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        List<String> sub = list.subList(0, 2); // 是一个视图

        list.remove(0); // 修改原ArrayList的个数
        System.out.println(list); // [2, 3]

        // java.util.ConcurrentModificationException 因为执行subList的时候保存了原ArrayList当时的modCount
        System.out.println(sub.get(0));
    }

    // 但是可以使用subList的remove方法改变元素的个数
    // 原因是同步了modCount的值
    public static void test4() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        List<String> sub = list.subList(0, 2);
        sub.remove("1"); // 可以通过subList提供的方法改变ArrayList的元素个数

        System.out.println(list);
    }

}
