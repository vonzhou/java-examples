package com.vonzhou.learn;

/**
 * Created by vonzhou on 16/8/28.
 */
public class PeriodicTask {
    public synchronized void execute() {
        System.out.println("task running...");
    }
}
