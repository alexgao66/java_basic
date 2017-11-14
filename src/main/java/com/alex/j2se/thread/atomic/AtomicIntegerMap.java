package com.alex.j2se.thread.atomic;

import com.google.common.collect.Maps;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gaojun on 2017/3/14.
 */
public class AtomicIntegerMap {

    public static void main(String[] args) {
        ConcurrentMap<String,AtomicInteger> map = Maps.newConcurrentMap();
        map.put("a_1", new AtomicInteger());

        AtomicInteger a1 = map.get("a_1");
        System.out.println("a_1:" + a1);
        System.out.println("a_2:" + map.get("a_2"));

        a1.incrementAndGet();
        a1.incrementAndGet();
        a1.incrementAndGet();
        System.out.println("a_1:" +map.get("a_1"));

        a1.set(0);
        System.out.println("a_1:" +map.get("a_1"));
    }
}
