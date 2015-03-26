package com.alex.j2se.thread.share;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试多线程环境下原子操作的反例
 * @author alex
 *
 */
public class AtomicTest implements Runnable {
	
	private int i = 0;
	
	/**
	 * 如果获取成员方法不使用synchronized，就有可能在increment方法中的两次递增之间获取值，
	 * 导致成员i不能被2整除。
	 * @return
	 */
	public /*synchronized*/ int getVal() {
		return i;
	}
	
	private synchronized void increment() {
		i++;
		i++;
	}
	
	public void run() {
		while(true) {
			increment();
		}
	}
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		AtomicTest test = new AtomicTest();
		service.execute(test);
		while(true) {
			int val = test.getVal();
			if(val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}
}
