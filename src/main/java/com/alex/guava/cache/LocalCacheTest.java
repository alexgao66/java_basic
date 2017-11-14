package com.alex.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by gaojun on 2017/3/14.
 */
public class LocalCacheTest {

    public static void main(String[] args) throws InterruptedException {
        Cache<String, Boolean> booleanCache =
                CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS).build();
        booleanCache.put("a", true);
        Thread.sleep(500);
        System.out.println("a:" + booleanCache.getIfPresent("a"));
        System.out.println("b:" + booleanCache.getIfPresent("b"));
        Thread.sleep(300);
        System.out.println("a:" + booleanCache.getIfPresent("a"));
        Thread.sleep(200);
        System.out.println("a:" + booleanCache.getIfPresent("a"));
        System.out.println("b:" + booleanCache.getIfPresent("b"));
    }

}
