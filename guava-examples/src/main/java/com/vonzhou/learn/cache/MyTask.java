package com.vonzhou.learn.cache;

import java.util.Random;

/**
 * @version 2016/11/19.
 */
public class MyTask implements Runnable {
    @Override
    public void run() {
        String res = CacheService.getInstance().get("hello" + new Random().nextInt(1000));
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(Thread.currentThread() + " -->" + res);
    }
}
