package com.vonzhou.learn.other;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @version 2017/3/17.
 */
public class ScheduleAtFixRateDemo {
    private static final ScheduledExecutorService single = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
        private int counter = 0;
        private String prefix = "LoadAppScheduler";

        public Thread newThread(Runnable r) {
            return new Thread(r, prefix + "-" + counter++);
        }
    });
    private static volatile boolean inited = false;

    public static void main(String[] args) {
        Runnable command = new Runnable() {
            public void run() {
                doTask();
            }
        };
        single.scheduleAtFixedRate(command, 1, 3, TimeUnit.SECONDS);
    }

    static int cnt = 0;

    private static void doTask() {
        try {
            cnt++;
            System.out.println("xxxxxxxxxxxxxxxxxxxxx");
            if (cnt == 5) {
                throw new RuntimeException("Fake");
            }
        } catch (Exception e) {
            log();
        }

    }

    private static void log() {
        System.out.println("log...............");
        throw new RuntimeException("again");
    }
}
