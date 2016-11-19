package com.vonzhou.learn.cache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version 2016/11/19.
 */
public class MultiThreadCompete {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 2000; i++) {
            executorService.execute(new MyTask());
        }
        executorService.shutdown();
    }
}
