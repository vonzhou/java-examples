package com.vonzhou.learn.guava.basics;

import com.google.common.base.MoreObjects;

/**
 * @version 2016/10/9.
 */
public class Foo {
    private String name;
    private String city;

    public Foo(String name, String city) {
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", this.name)
                .add("城市", this.city)
                .toString();
    }
}
