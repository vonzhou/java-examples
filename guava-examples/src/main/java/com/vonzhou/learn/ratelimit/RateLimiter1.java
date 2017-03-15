package com.vonzhou.learn.ratelimit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @version 2017/2/9.
 */
public class RateLimiter1 {
    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(1.0);
        for (int i = 0; i < 100; i++) {
            rateLimiter.acquire();
            System.out.println("do ........");
        }
    }
}
