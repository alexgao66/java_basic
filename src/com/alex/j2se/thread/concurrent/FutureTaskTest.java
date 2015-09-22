package com.alex.j2se.thread.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 测试FutureTask类<br>
 * 明确使用场景<br>
 * @author gao.jun 
 * @date 2015年9月22日
 *
 */
public class FutureTaskTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
		FutureTask<String> riceTask = new FutureTask<String>(new CallTask("rice", 3));
		new Thread(riceTask).start();
		FutureTask<String> waterTask = new FutureTask<String>(new CallTask("water", 1));
		new Thread(waterTask).start();
		waterTask.run();
		FutureTask<String> freshTask = new FutureTask<String>(new CallTask("fresh", 2));
		new Thread(freshTask).start();
		
		waterTask.get();
		System.out.println("water done");
		freshTask.get();
		System.out.println("fresh done");
		riceTask.get();
		System.out.println("rice done");
		
		System.out.println("all done! cost: " + String.valueOf(System.currentTimeMillis() - start));
		
	}
	
	private static class CallTask implements Callable<String> {
		
		private int cost;
		
		private String name;
		
		public CallTask(String name, int cost) {
			this.name = name;
			this.cost = cost;
		}

		@Override
		public String call() throws Exception {
			System.out.println(name + " start");
			Thread.sleep(cost * 1000);
			return this.name;
		}
		
	}
}
