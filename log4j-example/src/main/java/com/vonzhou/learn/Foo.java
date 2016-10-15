package com.vonzhou.learn;

import org.apache.log4j.Logger;

/**
 * @version 2016/10/12.
 */
public class Foo {
    private static final Logger logger = Logger.getLogger(Foo.class);
    public static void main(String[] args) {
        for(int i=0; i< 100; i++){
            logger.info("app logs output");
            logger.error("app error logs");
        }
    }
}
