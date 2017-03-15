package com.vonzhou.learn.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @version 2017/1/13.
 */
public class TestExpire {
    private static final int EXPIRE_DURATION_WRITE = 5; // 写后10s未重新写入或修改就移除

    private static final Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(5)
                    .expireAfterWrite(EXPIRE_DURATION_WRITE, TimeUnit.SECONDS).build();

    public static void main(String[] args) {
        try {
            cache.put("hello", "world");
            System.out.println(cache.getIfPresent("hello"));
            Thread.sleep(6000);

            System.out.println(cache.getIfPresent("hello"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
