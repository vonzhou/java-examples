package com.vonzhou.learn.jcu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @version 2017/2/27.
 */
public class FutureDemo {
    static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "hello";
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<String>> res = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            res.add(executor.submit(new Task()));
        }

        int count = 0;
        for (Future<String> f : res) {
            System.out.println((++count) + ": " + f.get());
        }

//        executor.shutdown();
    }
}
