package com.vonzhou.learn.section7.service;

import com.vonzhou.learn.section7.domain.Foo;

/**
 * @version 2017/3/17.
 */
public class DogService {

    private static DogService dogService = new DogService();

    public static DogService newInstance() {
        System.out.println("静态工厂方法....");
        return dogService;
    }

    public Foo createFoo(){
        System.out.println("实例工厂方法....");
        return new Foo();
    }
}
