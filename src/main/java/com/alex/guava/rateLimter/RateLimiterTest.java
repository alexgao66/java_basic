package com.alex.guava.rateLimter;

/**
 * Created by gaojun on 16/9/19.
 */
public class RateLimiterTest {

    public static void main(String[] args) {
        ResouceService rateLimiterTest = new ResouceService();
        for (int i = 0; i < 1000; ++i) {
            rateLimiterTest.resourceAccess();
        }
    }

}
