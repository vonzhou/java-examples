package com.vonzhou.learn.other;

import com.google.common.collect.Maps;

import java.io.DataOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @version 2017/2/15.
 */
public class ClassDemo {
    public static void main(String[] args) {
        test1();
    }

    /**
     * 底层存储　E[]
     */
    public static void test1() {
        List<Dog> list = Arrays.asList(new Dog(), new BigDog());

        System.out.println(list.toArray().getClass());

        Map<String, Object> dataMap = Maps.newHashMap();
        dataMap.put("x", list.toArray());
        Dog[] d = (Dog[]) dataMap.get("x");
        System.out.println((dataMap.get("x")).getClass());
    }

    /**
     * 底层存储  Object[] 所以会失去类型
     */
    public static void test2() {
        List<Dog> list = new ArrayList<>();
        list.add(new Dog());
        System.out.println(list.toArray().getClass()); // 其实此时数组类型已为 [Ljava.lang.Object;

        Map<String, Object> dataMap = Maps.newHashMap();
        dataMap.put("x", list.toArray());
        Dog[] d = (Dog[]) dataMap.get("x"); // 所以此时会抛出 ClassCastException
    }

    public static void test4() {
        List<Dog> list = new ArrayList<>();
        list.add(new Dog());

        Map<String, Object> dataMap = Maps.newHashMap();
        list.toArray();
        dataMap.put("x", list.toArray());

        Object[] oa = (Object[]) dataMap.get("x");
        System.out.println((Dog) oa[0]);

    }

    public static void test3() {
        Dog[] d = new Dog[1];
        Object[] o = new Dog[2];
        o[0] = new Dog();
        d = (Dog[]) o;

        System.out.println(d instanceof Object[]);
    }

    public static void test5() {
        Object[] objs = new Dog[1];
        System.out.println(objs.getClass()); // 类型是 [Lcom.vonzhou.learn.other.ClassDemo$Dog

        Object[] objs2 = new Object[1];
        objs2[0] = new Dog();
        System.out.println(objs2.getClass()); // 类型是 [Ljava.lang.Object;
    }

    public static class Dog {

    }

    public static class BigDog extends Dog {
    }
}
