package com.vonzhou.learn;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * 参考 `http://www.madbit.org/blog/programming/657/scheduling-with-spring-3-0/#sthash.1U25sVJG.dpbs`
 * Created by vonzhou on 16/8/28.
 */
public class PeriodicTaskMain {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:period-context.xml");
    }
}
