package com.vonzhou.learn;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @version 2016/11/7.
 */
public class Main2 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-schedule.xml");
        context.start();

    }

    @Scheduled(cron = "0 57 16 * * ?")
    public static void doTask() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("working----");
        }
    }
}
