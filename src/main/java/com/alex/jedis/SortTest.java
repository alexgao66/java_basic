package com.alex.jedis;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

public class SortTest {

	public static void main(String[] args) {
		sortHashset();
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
		
		client.close();
	}
	
	public static void sortHashset() {
		Jedis client = new Jedis("192.168.147.128", 6379);
		Map<String,String> carMap = new HashMap<String,String>();
		carMap.put("name", "benz");
		carMap.put("price", "200");
		carMap.put("country", "gernmany");
		client.hmset("car2", carMap);
		
		Map<String,String> carMap1 = new HashMap<String,String>();
		carMap1.put("name", "bmw");
		carMap1.put("price", "100");
		carMap1.put("country", "gernmany");
		client.hmset("car1", carMap1);
		
		Map<String,String> carMap2 = new HashMap<String,String>();
		carMap2.put("name", "audi");
		carMap2.put("price", "80");
		carMap2.put("country", "gernmany");
		client.hmset("car3", carMap2);
		
		client.lpush("cars", "car1");
		client.lpush("cars", "car2");
		client.lpush("cars", "car3");
		
		SortingParams sp = new SortingParams();
//		sp.alpha();
//		sp.by("car*->price");
		sp.get("car*->name");
//		sp.desc();
		
		System.out.println(client.sort("cars", sp));
		
		client.lrem("cars", 0, "car1");
		client.lrem("cars", 0, "car2");
		client.lrem("cars", 0, "car3");
		
		client.close();
	}
}
