package com.vonzhou.example;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author hzfengzhou
 * @version 2016/9/2.
 */
public class FooService {
    public static void sort(List<Foo> list) {
        Collections.sort(list, new Comparator<Foo>() {
            @Override
            public int compare(Foo o1, Foo o2) {
                return o1.getVal() > o2.getVal() ? 1 : -1;
            }
        });

    }
}
