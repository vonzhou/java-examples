package com.vonzhou.learn.other;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @version 2017/1/18.
 */
public class TimerExample {
    public static class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            try {
                System.out.println("do timer task");
                throw new RuntimeException("oops...");
            } catch (Throwable e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        Timer timer = new Timer(false); // 非 daemon
        timer.scheduleAtFixedRate(new MyTimerTask(), 1000, 1000);
        System.out.println("timer started...");
    }
}
