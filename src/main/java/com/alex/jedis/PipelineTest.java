package com.alex.jedis;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class PipelineTest {

	public static void main(String[] args) {
		pipelinePerformanceTest();
	}
	
	public static Jedis getClient() throws URISyntaxException {
		return new Jedis(new URI("redis", null, "192.168.147.128", 6379, "/1", null, null));
	}
	
	/**
	 * 使用管道
	 * 2015年10月22日<br>
	 * @author gao.jun
	 * @throws URISyntaxException
	 */
	public static void pipelineTest() throws URISyntaxException {
		Jedis client = getClient();
		Pipeline pl = client.pipelined();
		pl.set("p1", "v1");
		List<Object> objs = pl.syncAndReturnAll();
		for(Object obj : objs) {
			System.out.println(obj);
		}
		client.close();
	}
	
	/**
	 * 测试管道和直连的性能差别，10倍左右
	 * 2015年10月22日<br>
	 * @author gao.jun
	 */
	public static void pipelinePerformanceTest() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Jedis client = null;
				try {
					client = getClient();
					Pipeline pl = client.pipelined();
					long start = System.currentTimeMillis();
					for(int i = 0; i < 1000; i++) {
						pl.set("p1" + i, "v1" + i);
					}
					pl.syncAndReturnAll();
					System.out.println("pipeline cost:" + (System.currentTimeMillis() - start));
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}finally {
					if(client != null) {
						client.close();
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Jedis client = null;
				try {
					client = getClient();
					long start = System.currentTimeMillis();
					for(int i = 0; i < 1000; i++) {
						client.set("p2" + i, "v2" + i);
					}
					System.out.println("direct cost:" + (System.currentTimeMillis() - start));
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}finally {
					if(client != null) {
						client.close();
					}
				}
			}
		}).start();
	}
}
