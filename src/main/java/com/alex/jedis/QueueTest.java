package com.alex.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class QueueTest {

	public static void main(String[] args) {
		pubSub();
	}
	
	public static void queueTest1() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Jedis client = new Jedis("192.168.147.128", 6379);
				for(int i = 0; i < 10; ++i) {
					System.out.println("push: " + "v" + i);
					client.lpush("queue1", "v" + i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				client.close();
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Jedis client = new Jedis("192.168.147.128", 6379);
				for(int i = 0; i < 10; ++i) {
					System.out.println("pop: " + client.brpop(0, "queue1"));
				}
				client.close();
			}
		}).start();
	}
	
	public static void priorityQueueTest() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Jedis client = new Jedis("192.168.147.128", 6379);
				for(int i = 1; i <= 2; ++i) {
					for(int j = 0; j < 10; ++j) {
						System.out.println("q" + i +" push: " + "v" + j);
						client.lpush("q" + i, "v" + j);
					}
				}
				client.close();
			}
		}).start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Jedis client = new Jedis("192.168.147.128", 6379);
				for(int i = 0; i < 20; ++i) {
					System.out.println("pop: " + client.brpop(0, "q2", "q1"));
				}
				client.close();
			}
		}).start();
	}
	
	public static void pubSub() {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Jedis client = new Jedis("192.168.147.128", 6379);
				for(int j = 0; j < 10; ++j) {
					System.out.println("push: " + "v" + j);
					client.publish("q1", "v" + j);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				client.close();
			}
		}).start();
		
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Jedis client = new Jedis("192.168.147.128", 6379);
				client.subscribe(new JedisPubSub() {
					@Override
					public void onMessage(String channel, String message) {
						System.out.println("s1, "+ channel + ":" + message);
					}
				}, "q1");
				client.close();
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Jedis client = new Jedis("192.168.147.128", 6379);
				client.subscribe(new JedisPubSub() {
					@Override
					public void onMessage(String channel, String message) {
						System.out.println("s2, "+ channel + ":" + message);
					}
				}, "q1");
				client.close();
			}
		}).start();
		
	}
}
