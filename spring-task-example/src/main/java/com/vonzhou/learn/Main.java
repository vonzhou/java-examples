package com.vonzhou.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by vonzhou on 16/8/27.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:service-context.xml");
        TaskExecutorExample taskExecutorExample = (TaskExecutorExample)context.getBean("taskExecutorExample");
        taskExecutorExample.printMessages();

    }
}
