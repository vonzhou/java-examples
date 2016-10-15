package com.vonzhou.learn.foo;

import org.apache.log4j.Logger;

/**
 * @version 2016/10/12.
 */
public class Foo2 {
    private static final Logger logger = Logger.getLogger(Foo2.class);
    public static void main(String[] args) {
        for(int i=0; i< 100; i++){
            logger.info("foo logs output");
            logger.error("foo error logs");
        }
    }
}
