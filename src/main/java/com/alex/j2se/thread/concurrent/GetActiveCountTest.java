package com.alex.j2se.thread.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 测试线程池的getActiveCount
 * @author gao.jun 
 * @date 2015年9月22日
 *
 */
public class GetActiveCountTest {

	static ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(1);

	public static void main(String[] args) {
		for (int i = 0; i < 3; ++i) {
			System.out.println("i: " + i);
			System.out.println("d: " + executor.getActiveCount());
			executor.submit(new ActiveCountTask(i));
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("a: " + executor.getActiveCount());
			try {
				Thread.currentThread().sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("b: " + executor.getActiveCount());
			System.out.println();
		}
		executor.shutdown();
	}

	static class ActiveCountTask implements Runnable {

		private int i;

		public ActiveCountTask(int i) {
			this.i = i;
		}


		@Override
		public void run() {
			System.out.println("c: " + executor.getActiveCount());
			try {
				Thread.currentThread().sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (i == 1) {
				Thread.currentThread().interrupt();
				System.out.println("interrupt");
			}
			System.out.println("e: " + executor.getActiveCount());
		}
	}
}
