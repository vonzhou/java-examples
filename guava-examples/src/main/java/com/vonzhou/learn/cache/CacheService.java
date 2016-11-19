package com.vonzhou.learn.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @version 2016/11/19.
 */
public class CacheService {
    private static final int EXPIRE_DURATION_WRITE = 10; // 写后10s未重新写入或修改就移除

    private static final LoadingCache<String, String> cache = CacheBuilder.newBuilder().maximumSize(3)
                    .expireAfterWrite(EXPIRE_DURATION_WRITE, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {
                        @Override
                        public String load(String key) throws Exception {
                            return key.toUpperCase();
                        }
                    });

    private CacheService() {

    }

    public String get(String key) {
        try {
            return cache.get(key);
        } catch (ExecutionException e) {
            System.out.println("error..");
        }
        return "";
    }

    private static CacheService instance = new CacheService();

    public static CacheService getInstance() {
        return instance;
    }

}
