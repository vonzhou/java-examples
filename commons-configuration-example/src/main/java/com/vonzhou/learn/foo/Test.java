package com.vonzhou.learn.foo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @version 2017/3/15.
 */
public class Test {
    public static void main(String[] args) {


        test2();

    }

    private static void test1() {
        Set<Integer> a = new HashSet<Integer>(Arrays.asList(1,2));
        Set<Integer> b = new HashSet<Integer>(Arrays.asList(1,2,3));


        a.removeAll(b);
//        b.removeAll(a);
        System.out.println(a);
        System.out.println(b);
    }


    private static void test2() {
        Set<Integer> a = new HashSet<Integer>(Arrays.asList(4, 5));
        Set<Integer> b = new HashSet<Integer>(Arrays.asList(1,2,3));


        a.removeAll(b);
//        b.removeAll(a);
        System.out.println(a);
        System.out.println(b);
    }


}
