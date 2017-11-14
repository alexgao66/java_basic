package com.alex.guava.rateLimter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * Created by gaojun on 16/9/19.
 */
public class ResouceService {

    private RateLimiter rateLimiter;

    public ResouceService() {
        rateLimiter = RateLimiter.create(1);
    }

    public void resourceAccess() {
        boolean allowed = rateLimiter.tryAcquire(1, TimeUnit.SECONDS);
        if(allowed) {
            System.out.println("process....");
        }else {
            System.out.println("fail.....");
        }
    }
}
