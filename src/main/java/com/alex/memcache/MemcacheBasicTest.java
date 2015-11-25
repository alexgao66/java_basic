package com.alex.memcache;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.Counter;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class MemcacheBasicTest {
	
	public static void main(String[] args) {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("localhost:11211"));
		builder.setFailureMode(true);
		builder.setConnectionPoolSize(10);
		builder.setConnectTimeout(100);
		MemcachedClient client = null;
		try {
			client = builder.build();
			System.out.println("set: " + client.set("key-1", 0, "v1"));
			System.out.println("get: " + client.get("key-1"));
			System.out.println("incr 0: " + client.incr("key-2", 0));
			System.out.println("incr 1: " + client.incr("key-2", 1));
			System.out.println("get: " + client.get("key-2"));
			Counter counter = client.getCounter("key-3");
			System.out.println("Counter get: " + counter.get());
			System.out.println("Counter incrementAndGet: " + counter.incrementAndGet());
			System.out.println("Counter addAndGet 2: " + counter.addAndGet(2));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		} finally {
			if(client != null) {
				try {
					client.shutdown();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
