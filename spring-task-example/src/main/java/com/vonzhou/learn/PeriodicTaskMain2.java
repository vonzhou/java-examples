package com.vonzhou.learn;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by vonzhou on 16/8/28.
 */
public class PeriodicTaskMain2 {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:period-context2.xml");
    }
}
