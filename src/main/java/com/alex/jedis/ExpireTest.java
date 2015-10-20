package com.alex.jedis;

import redis.clients.jedis.Jedis;

public class ExpireTest {

	public static void main(String[] args) throws InterruptedException {
		testExpire();
	}
	
	/**
	 * 测试有效时间和剩余有效期
	 * 2015年10月19日<br>
	 * @author gao.jun
	 * @throws InterruptedException
	 */
	public static void testExpire() throws InterruptedException {
		Jedis client = new Jedis("192.168.147.128", 6379);
		client.set("exp-1", "val-1");
		client.expire("exp-1", 2);
		System.out.println("before:" + client.get("exp-1"));
		System.out.println("ttl:" + client.ttl("exp-1"));
		Thread.sleep(3000);
		System.out.println("after:" + client.get("exp-1"));
		client.close();
	}
}
