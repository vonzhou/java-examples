package com.vonzhou.learning.init;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @version 2016/11/13.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-context.xml");
        Foo f = (Foo)context.getBean("foo");
        Foo f2 = (Foo)context.getBean("foo2");
        Bar b = (Bar)context.getBean("bar");
    }
}
