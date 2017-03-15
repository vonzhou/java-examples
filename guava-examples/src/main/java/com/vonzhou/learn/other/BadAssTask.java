package com.vonzhou.learn.other;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @version 2017/1/17.
 */
public class BadAssTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Sleeping ...");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Throwing ... ");
//        throw new RuntimeException("bad ass!");
    }

    public static void main(String[] args) {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new BadAssTask(), 1, 1, TimeUnit.SECONDS);
    }

}
