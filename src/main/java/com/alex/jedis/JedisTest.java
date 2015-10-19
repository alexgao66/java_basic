package com.alex.jedis;

import redis.clients.jedis.Jedis;

public class JedisTest {

	
	public static Jedis getClient() {
		return new Jedis("192.168.147.128", 6379);
	}
	
	

	public static void main(String[] args) {
		System.out.println("------String test--------");
		stringTest();
		System.out.println("------Key test--------");
		keyTest();
		System.out.println("------Type test--------");
		typeTest();
	}



	public static void keyTest() {
		Jedis client = getClient();
		System.out.println("keys，全匹配：" + client.keys("bar"));
		System.out.println("keys，bar?，单个字符匹配：" + client.keys("bar?"));
		System.out.println("keys，bar*，0或多个字符匹配：" + client.keys("bar*"));
		System.out.println("exists，是否存在：" + client.exists("bar"));
		System.out.println("dbSize，key个数：" + client.dbSize());
		client.close();
	}
	
	
	public static void stringTest() {
		Jedis client = getClient();
		System.out.println("String set：" + client.set("bar1", "bar1Value"));
		System.out.println("String set：" + client.set("bar12", "bar12Value"));
		System.out.println("String get，bar1：" + client.get("bar1"));
		System.out.println("String del：" + client.del("bar1"));
		
		System.out.println("String incr，bar99：" + client.incr("bar99"));
		System.out.println("String get，bar99：" + client.get("bar99"));
		System.out.println("String incrBy，bar99,10 ：" + client.incrBy("bar99", 10));
		System.out.println("String get，bar99：" + client.get("bar99"));
		System.out.println("String decrBy，bar99,10 ：" + client.decrBy("bar99", 10));
		System.out.println("String get，bar99：" + client.get("bar99"));
		System.out.println("String set，bar99,\"1\"：" + client.set("bar99", "1"));
		System.out.println("String get，bar99：" + client.get("bar99"));
		
		System.out.println("String append，bar12：" + client.append("bar12", "-append"));
		System.out.println("String get，bar12：" + client.get("bar12"));
		
		System.out.println("String strlen，bar12：" + client.strlen("bar12"));
		
		System.out.println("String mset，\"bar13\",\"13\",\"bar14\",\"val14\"：" + client.mset("bar13","13","bar14","val14"));
		System.out.println("String get，bar14：" + client.get("bar14"));
		System.out.println("String msetnx，\"bar13\",\"133\",\"bar15\",\"val15\"：" + client.msetnx("bar13","133","bar15","val15"));
		System.out.println("String get，val13：" + client.get("bar13"));
		client.close();
	}
	
	public static void typeTest() {
		Jedis client = getClient();
		System.out.println("type：" + client.type("bar"));
		client.close();
	}
}
