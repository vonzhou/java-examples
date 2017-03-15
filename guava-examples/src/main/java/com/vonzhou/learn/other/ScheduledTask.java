package com.vonzhou.learn.other;

import com.vonzhou.learn.cache.MyTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @version 2017/1/18.
 */
public class ScheduledTask {
    public static class MyTask implements Runnable {

        public void run() {
            try {
                // do the work
            } catch (Throwable e) {
                // handle or ignore
            }
            System.out.println("do a task");
            throw new RuntimeException("oops...");
        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(new MyTask(), 1, 1, TimeUnit.SECONDS);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(ses.isShutdown());
        ses.scheduleAtFixedRate(new MyTask(), 1, 1, TimeUnit.SECONDS);
    }
}
