package com.alex.j2se.thread.evenNumChecker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 核查偶数生成器中生成数字是不是偶数，如果不是偶数，则调用cancel方法终止生成器
 * @author alex
 *
 */
public class EvenChecker implements Runnable {
	
	private IntGenerator generator;
	
	public EvenChecker(IntGenerator generator) {
		this.generator = generator;
	}

	public void run() {
		while(!this.generator.isCanceled()) {
			int val = this.generator.next();
			if(val%2 != 0) {
				System.out.println(val + " not even!");
				this.generator.cancel();
			}
		}
	}
	
	/**
	 * 多个任务共享同一个偶数生成器
	 * @param generator
	 * @param count
	 */
	public static void test(IntGenerator generator,int count) {
		System.out.println("Press Ctrl-C to exit");
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < count; ++i) {
			exec.execute(new EvenChecker(generator));
		}
		exec.shutdown();
	}
	
	public static void test(IntGenerator generator) {
		test(generator, 10);
	}
	
}
