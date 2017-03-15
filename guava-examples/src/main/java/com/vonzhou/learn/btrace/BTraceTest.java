package com.vonzhou.learn.btrace;

import java.util.Random;

/**
 * @version 2017/2/21.
 */
public class BTraceTest {

    public static void main(String[] args) throws Exception {
        Random random = new Random();

        // 计数器
        Counter counter = new Counter();
        while (true) {
            // 每次增加随机值
            counter.add(random.nextInt(10));
            Thread.sleep(1000);
        }
    }
}
