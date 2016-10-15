package com.vonzhou.learn.guava.basics;

import com.google.common.base.Objects;

/**
 * JDK 1.7 加入了对应的 equals, hashCode 实现
 * Created by vonzhou on 16/8/21.
 */
public class CommonObjectUtilitiesDemo {
    public static void main(String[] args) {
//        equalFunc();
//        toStringFunc();
        cmpFunc();
    }

    /**
     * equal / hashCode 方法
     */
    public static void equalFunc() {
        System.out.println(Objects.equal("a", "a")); // returns true
        System.out.println(Objects.equal(null, "a"));// returns false
        System.out.println(Objects.equal("a", null));// returns false
        System.out.println(Objects.equal(null, null));// returns true

        /**
         * 确保每个field的hashCode方法要正确实现, Objects.hashCode只是使用 31 * + 组合起来
         * */
        int h = Objects.hashCode("abc", 1, 3.9);
        System.out.println(h);
    }

    /**
     * toStringBuilder的使用
     */
    public static void toStringFunc() {
        Foo f = new Foo("fz", "China");
        System.out.println(f);
    }

    /**
     * ComparisonChain 将比较的字段连接起来, 清晰
     */
    public static void cmpFunc() {
        Person p1 = new Person("zhou", "f", 123);
        Person p2 = new Person("zhou", "fz", 123);
        System.out.println(p1.compareTo(p2));  // -1

        Person p3 = new Person("zhou", null, 123);
        Person p4 = new Person("zhou", "f", 123);
        System.out.println(p3.compareTo(p4));  // 1

        Person p5 = new Person(null, "f", 123);
        Person p6 = new Person("zhou", "fz", 123);
        System.out.println(p5.compareTo(p6));   // NullPointerException
    }


}




