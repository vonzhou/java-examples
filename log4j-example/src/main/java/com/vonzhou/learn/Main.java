package com.vonzhou.learn;

import org.apache.log4j.Logger;

/**
 * @version 2016/10/12.
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        for(int i=0; i< 100; i++){
            logger.info("some logs output");
            logger.error("some error logs");
        }
    }
}
