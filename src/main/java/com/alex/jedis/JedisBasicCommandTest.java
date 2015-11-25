package com.alex.jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

public class JedisBasicCommandTest {

	
	public static Jedis getClient() {
		return new Jedis("192.168.147.128", 6379);
	}
	
	public static void main(String[] args) {
		keyTest();
	}



	public static void keyTest() {
		Jedis client = getClient();
		System.out.println("keys,full match:" + client.keys("bar"));
		System.out.println("keys,bar?,单个字符match:" + client.keys("bar?"));
		System.out.println("keys,bar*,0或多个字符match:" + client.keys("bar*"));
		System.out.println("exists,是否存在:" + client.exists("bar"));
		System.out.println("dbSize,key个数:" + client.dbSize());
		client.close();
	}
	
	
	public static void stringTest() {
		Jedis client = getClient();
		System.out.println("String set:" + client.set("bar1", "bar1Value"));
		System.out.println("String set:" + client.set("bar12", "bar12Value"));
		System.out.println("String get,bar1:" + client.get("bar1"));
		System.out.println("String del:" + client.del("bar1"));
		
		System.out.println("String incr,bar99:" + client.incr("bar99"));
		System.out.println("String get,bar99:" + client.get("bar99"));
		System.out.println("String incrBy,bar99,10 :" + client.incrBy("bar99", 10));
		System.out.println("String get,bar99:" + client.get("bar99"));
		System.out.println("String decrBy,bar99,10 :" + client.decrBy("bar99", 10));
		System.out.println("String get,bar99:" + client.get("bar99"));
		System.out.println("String set,bar99,\"1\":" + client.set("bar99", "1"));
		System.out.println("String get,bar99:" + client.get("bar99"));
		
		System.out.println("String append,bar12:" + client.append("bar12", "-append"));
		System.out.println("String get,bar12:" + client.get("bar12"));
		
		System.out.println("String strlen,bar12:" + client.strlen("bar12"));
		
		System.out.println("String mset,\"bar13\",\"13\",\"bar14\",\"val14\":" + client.mset("bar13","13","bar14","val14"));
		System.out.println("String get,bar14:" + client.get("bar14"));
		System.out.println("String msetnx,\"bar13\",\"133\",\"bar15\",\"val15\":" + client.msetnx("bar13","133","bar15","val15"));
		System.out.println("String get,val13:" + client.get("bar13"));
		client.close();
	}
	
	public static void hashsetTest() {
		Jedis client = getClient();
		
		System.out.println("hashset hset, car_1,name,宝马 :" + client.hset("car_1", "name", "宝马"));
		System.out.println("hashset hget, car_1,name :" + client.hget("car_1", "name"));
		
		Map<String,String> fields = new HashMap<String,String>();
		fields.put("country", "german");
		fields.put("price", "100w");
		System.out.println("hashset hmset, car_1,price :" + client.hmset("car_1", fields));
		System.out.println("hashset hmget, car_1,name,country,price :" + client.hmget("car_1", "name", "country", "price"));
		
		System.out.println("hashset hgetAll, car_1 :" + client.hgetAll("car_1"));
		
		System.out.println("hashset hexists, car_1,name :" + client.hexists("car_1", "name"));
		System.out.println("hashset hexists, car_1,owner :" + client.hexists("car_1", "owner"));
		
		System.out.println("hashset hsetnx, car_1,owner,alex :" + client.hsetnx("car_1", "owner", "alex"));
		System.out.println("hashset hgetAll, car_1 :" + client.hgetAll("car_1"));
		
		System.out.println("hashset hincrBy, car_1,years,1 :" + client.hincrBy("car_1", "years", 1));
		System.out.println("hashset hgetAll, car_1 :" + client.hgetAll("car_1"));
		
		System.out.println("hashset hdel, car_1,years,owner :" + client.hdel("car_1", "years", "owner"));
		System.out.println("hashset hgetAll, car_1 :" + client.hgetAll("car_1"));
		
		client.close();
	}
	
	public static void listTest() {
		Jedis client = getClient();
		
		System.out.println("list llen, list_1 :" + client.llen("list_1"));
		
		System.out.println("list rpush, list_1,v1,v2,v1 :" + client.rpush("list_1", "v1", "v2", "v1"));
		System.out.println("list rpush, list_1,v4,v5 :" + client.rpush("list_1", "v4", "v5"));
		System.out.println("list lrange, list_1,0,100 :" + client.lrange("list_1", 0, 100));
		System.out.println("list lrange, list_1,0,2 :" + client.lrange("list_1", 0, 2));
		
		System.out.println("list lrem, list_1,0,v1 :" + client.lrem("list_1", 0, "v1"));
		System.out.println("list lrange, list_1,0,2 :" + client.lrange("list_1", 0, 100));
		
		System.out.println("list lpop, list_1 :" + client.lpop("list_1"));
		
		System.out.println("list rpop, list_1 :" + client.rpop("list_1"));
		
		client.close();
	}
	
	public static void setTest() {
		Jedis client = getClient();
		
		System.out.println("set sadd, set_1,v1,v2,v1 :" + client.sadd("set_1", "v1", "v2", "v1"));
		System.out.println("set smembers, set_1 :" + client.smembers("set_1"));
		System.out.println("set sismember, set_1,v1 :" + client.sismember("set_1", "v1"));
		System.out.println("set sismember, set_1,v3 :" + client.sismember("set_1", "v3"));
		
		System.out.println("set sadd, set_1,v3 :" + client.sadd("set_1", "v3"));
		System.out.println("set sadd, set_2,v2,v3,v4 :" + client.sadd("set_2", "v2", "v3", "v4"));
		System.out.println("set sdiff, set_1,set_2 :" + client.sdiff("set_1","set_2"));
		System.out.println("set sinter, set_1,set_2 :" + client.sinter("set_1","set_2"));
		System.out.println("set sunion, set_1,set_2 :" + client.sunion("set_1","set_2"));
		
		client.close();
	}
	
	public static void zsetTest() {
		Jedis client = getClient();
		System.out.println("zset zadd, zset_1,2,v2 :" + client.zadd("zset_1", 2, "v2"));
		System.out.println("zset zadd, zset_1,1,v1 :" + client.zadd("zset_1", 1, "v1"));
		System.out.println("zset zadd, zset_1,1,v1 :" + client.zadd("zset_1", 0, "v3"));
		
		System.out.println("zset zscore, zset_1,v2 :" + client.zscore("zset_1", "v2"));
		
		System.out.println("zset zadd, zset_1,1,v1 :" + client.zadd("zset_1", 1, "v11"));
		System.out.println("zset zadd, zset_1,1,v1 :" + client.zadd("zset_1", 1, "v12"));
		System.out.println("zset zadd, zset_1,1,v1 :" + client.zadd("zset_1", 1, "v122"));
		System.out.println("zset zadd, zset_1,1,v1 :" + client.zadd("zset_1", 1, "v134"));
		
		System.out.println("zset zrange, zset_1,0,1000:" + client.zrange("zset_1", 0, 1000));
		Set<Tuple> tuples = client.zrangeWithScores("zset_1", 0, 1000);
		for(Tuple t : tuples) {
			System.out.println("zrangeWithScores, tuple el:" + t.getElement() + ", score:" + t.getScore());
		}
		
		System.out.println("zset zrangeByScore, zset_1,0,1:" + client.zrangeByScore("zset_1", 0, 1));
		
		System.out.println("zset zrangeByScore, zset_1,0,1:" + client.zincrby("zset_1", 20, "v12"));
		
		System.out.println("zset zrangeByScore, zset_1,0,1:" + client.zrangeByScore("zset_1", 0, 90));
		
		client.close();
	}
	
	
	
	public static void typeTest() {
		Jedis client = getClient();
		System.out.println("type:" + client.type("bar"));
		client.close();
	}
}
