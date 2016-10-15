package com.vonzhou.learn.guava.basics;

import com.google.common.collect.ImmutableList;

/**
 * 没有体会到优势?
 * TODO
 * @see `https://github.com/google/guava/wiki/ImmutableCollectionsExplained`
 * Created by vonzhou on 16/7/24.
 */
public class ImmutableCollectionsDemo {

    public static final ImmutableList<String> LOCATIONS = ImmutableList.of(
            "Hangzhou",
            "Wuhan",
            "Shiyan",
            "Dangshan"
    );

    public static void main(String[] args) {
        System.out.println(LOCATIONS.indexOf("Wuhan"));
        foo();

    }


    public static void foo(){
        ImmutableList<String> defensiveCopy = ImmutableList.copyOf(LOCATIONS);
        System.out.println(defensiveCopy.get(0));
    }
}

