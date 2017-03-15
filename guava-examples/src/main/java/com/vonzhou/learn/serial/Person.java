package com.vonzhou.learn.serial;

import java.io.Serializable;

/**
 * @version 2017/2/10.
 */
public class Person implements Serializable {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" + "age=" + age + ", name='" + name + '\'' + '}';
    }
}