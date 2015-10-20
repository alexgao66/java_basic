package com.alex.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

public class JedisTrancationTest {

	public JedisTrancationTest() {
	}
	
	public static Jedis getClient() {
		return new Jedis("192.168.147.128", 6379);
	}

	public static void main(String[] args) {
		discardTest();
	}
	
	/**
	 * 使用事务，出现异常时不报错，不回滚
	 * 2015年10月19日<br>
	 * @author gao.jun
	 */
	public static void transactionTest() {
		Jedis client = getClient();
		Transaction t = client.multi();
		t.set("key-b", "val-b");
		t.set("key-2", "val-2");
		t.incr("key-2");
		Response<String> val1 = t.get("key-2");
		t.exec();
		client.disconnect();
		
		System.out.println(val1.get());
	}
	
	/**
	 * 不使用事务，出现异常报错，不回滚
	 * 2015年10月19日<br>
	 * @author gao.jun
	 */
	public static void noTransactionTest() {
		Jedis client = getClient();
//		Transaction t = client.multi();
		client.set("key-a", "val-a");
		client.set("key-1", "val-1");
		client.incr("key-1");
//		Response<String> val1 = t.get("key-1");
//		t.exec();
		client.disconnect();
		
//		System.out.println(val1.get());
	}
	
	/**
	 * 使用discard取消事务
	 * 2015年10月19日<br>
	 * @author gao.jun
	 */
	public static void discardTest() {
		Jedis client = getClient();
		Transaction t = client.multi();
		t.set("key-c", "val-c");
		t.set("key-1", "val-11");
		t.discard();
		client.disconnect();
		
		System.out.println(client.get("key-1"));
	}
	
	public static void watchTest() {
		Jedis client = getClient();
		
		Transaction t = client.multi();
		t.set("key-1", "val-12");
		t.watch("key-1");
		
		t.exec();
		
		client.disconnect();
		
		System.out.println(client.get("key-1"));
	}
	
	public static void print() {
		Jedis client = getClient();
		System.out.println(client.get("key-1"));
		client.disconnect();
	}
}
