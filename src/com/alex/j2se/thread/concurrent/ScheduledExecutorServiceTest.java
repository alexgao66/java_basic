package com.alex.j2se.thread.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ScheduledExecutorServiceTest {
	
	public static ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(1);  
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("start...");
		// 固定频率调用
		scheduExec.scheduleAtFixedRate(new Task(), 0, 4000, TimeUnit.MILLISECONDS);
		// 完成后由一定的延时
		scheduExec.scheduleWithFixedDelay(new Task(), 0, 4000, TimeUnit.MILLISECONDS);
		Thread.sleep(20000);
		scheduExec.shutdown();
		System.out.println("stop...");
	}
	
	private static class Task implements Runnable {
		
		private int count = 1;

		@Override
		public void run() {
			try {
				System.out.println("run: " + count);
				long start = System.currentTimeMillis();
				Thread.sleep(5000);
				System.out.println("finish..." + (System.currentTimeMillis() - start));
				++count;
			} catch (Exception e) {
				System.out.println("错误！");
				e.printStackTrace();
			}
		}
		
	}
}
