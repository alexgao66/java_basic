package com.alex.jedis;

import redis.clients.jedis.Jedis;

public class RamOptimize {

	public static void main(String[] args) {
		objEncode();
	}
	
	public static void objEncode() {
		Jedis client = new Jedis("192.168.147.128", 6379);
		System.out.println(client.objectEncoding("car_1"));
		System.out.println(client.objectEncoding("bar"));
		System.out.println(client.objectEncoding("bar15"));
		System.out.println(client.objectEncoding("list_1"));
		System.out.println(client.objectEncoding("set_1"));
		System.out.println(client.objectEncoding("zset_1"));
		client.close();
	}
}
