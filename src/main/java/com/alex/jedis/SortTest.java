package com.alex.jedis;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

public class SortTest {

	public static void main(String[] args) {
		sortList();
	}
	
	public static void sortList() {
		Jedis client = new Jedis("192.168.147.128", 6379);
		System.out.println(client.lrange("list_1", 0, -1));
		client.rpush("list_1", "V3");
		SortingParams sp = new SortingParams();
		sp.alpha();
		sp.desc();
		
		System.out.println(client.sort("list_1", sp));
		sp.limit(0, 5);
		System.out.println(client.sort("list_1", sp));
		
		System.out.println(client.zrange("zset_1", 0, -1));
		System.out.println(client.sort("zset_1", sp));
		
		Map<String,String> carMap = new HashMap<String,String>();
		carMap.put("name", "benz");
		carMap.put("price", "200");
		carMap.put("country", "gernmany");
		client.hmset("car_2", carMap);
		
		Map<String,String> carMap1 = new HashMap<String,String>();
		carMap1.put("name", "bmw");
		carMap1.put("price", "100");
		carMap1.put("country", "gernmany");
		client.hmset("car_1", carMap1);
		
		System.out.println(client.hmget("car_1", "name"));
//		sp.by("car_*->price");
//		System.out.println(client.sort("car_1", sp));
		
		client.close();
	}
}
