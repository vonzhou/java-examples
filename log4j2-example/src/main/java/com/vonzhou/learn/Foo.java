package com.vonzhou.learn;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * https://logging.apache.org/log4j/2.x/manual/configuration.html  讲的很清晰了
 * http://stackoverflow.com/questions/14733698/log4j-2-configuration-issue
 * https://springframework.guru/log4j-2-configuration-using-properties-file/
 *
 * @version 2016/10/12.
 */
public class Foo {
    //    private static final Logger logger = Logger.getLogger(Foo.class);
    private static final Logger logger = LogManager.getLogger(Foo.class);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            logger.info("app logs output");
            logger.error("app error logs");
        }
    }
}
