package com.vonzhou.example;

import java.util.Arrays;
import java.util.List;

/**
 * @author hzfengzhou
 * @version 2016/9/2.
 */
public class Main {
    public static void main(String[] args) {
        List<Foo> list = Arrays.asList(new Foo(1), new Foo(1), new Foo(0), new Foo(100),new Foo(-20));
        FooService.sort(list);
    }

}
