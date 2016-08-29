package com.vonzhou.learn.task;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by vonzhou on 16/8/28.
 */
public class PeriodicTask2 {

    @Scheduled(fixedRate = 10 * 1000)
    public synchronized void execute() {
        System.out.println("Hello world");
    }
}
