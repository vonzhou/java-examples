package com.vonzhou.learn.multithread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by vonzhou on 2017/1/17.
 */
public class ScheduleProblem {
    public static class MyTask implements Runnable {
        @Override
        public void run() {
            doTask();
        }
    }

    public static void doTask() {
        System.out.println(4 / 2);
        System.out.println(1 / 0);
    }

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new MyTask(), 1, 1, TimeUnit.SECONDS);
    }
}
