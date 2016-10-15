package com.vonzhou.learn.guava.basics;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

/**
 * @version 2016/10/9.
 */
public class Person implements Comparable<Person> {
    private String lastName;
    private String firstName;
    private int zipCode;

    public Person(String lastName, String firstName, int zipCode) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.zipCode = zipCode;
    }

    public int compareTo(Person that) {
        /*
        int cmp = lastName.compareTo(other.lastName);
        if (cmp != 0) {
            return cmp;
        }
        cmp = firstName.compareTo(other.firstName);
        if (cmp != 0) {
            return cmp;
        }
        return Integer.compare(zipCode, other.zipCode);
        */
        return ComparisonChain.start()
                .compare(this.lastName, that.lastName)  // 默认比较的时候是不能为 Null的
                .compare(this.firstName, that.firstName, Ordering.natural().nullsLast())
                .compare(this.zipCode, that.zipCode)
                .result();
    }
}