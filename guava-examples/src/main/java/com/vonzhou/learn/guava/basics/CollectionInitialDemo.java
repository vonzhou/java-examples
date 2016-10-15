package com.vonzhou.learn.guava.basics;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * http://insightfullogic.com/2011/Oct/21/5-reasons-use-guava/
 * 简化集合类声明方式
 *
 * @version 2016/10/8.
 */
public class CollectionInitialDemo {
    public static void main(String[] args) {
        final Map<String, Map<String, Integer>> lookup = new HashMap<String, Map<String, Integer>>();

        /**
         * java 1.7
         */
        final Map<String, Map<String, Integer>> lookup2 = new HashMap<>();

        /**
         * Guava
         */
        final Map<String, Map<String, Integer>> lookup3 = Maps.newHashMap();
    }
}
