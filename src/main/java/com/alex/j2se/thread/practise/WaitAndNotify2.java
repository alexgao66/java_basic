package com.alex.j2se.thread.practise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试使用轮询检测、使用wait两种方式进行线程协助
 * @author alex
 *
 */
public class WaitAndNotify2 {
	
	private static Boolean flag = false;

	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
//		es.submit(new SubThreadA());
//		es.submit(new SubThreadB());
		SubThreadAWait a = new SubThreadAWait();
		es.submit(a);
		es.submit(new SubThreadBWait(a));
		es.shutdown();
	}
	
	private static class SubThreadA implements Runnable {

		public void run() {
			System.out.println("SubThreadA start...");
			System.out.println("SubThreadA flag:" + flag);
			try {
				Thread.sleep(1100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			flag = true;
			System.out.println("SubThreadA end...");
		}
	}
	
	private static class SubThreadB implements Runnable {

		public void run() {
			long start = System.currentTimeMillis();
			while(true) {
				if(flag){
					flag = false;
					break;
				}
			}
			System.out.println("SubThreadB busy time:" + (System.currentTimeMillis() - start));
		}
	}
	
	private static class SubThreadAWait implements Runnable {

		public void run() {
			System.out.println("SubThreadA start...");
			try {
				Thread.sleep(1100);
			} catch (Exception e) {
				e.printStackTrace();
			}
			synchronized (this) {
				this.notify();
			}
			System.out.println("SubThreadA end...");
		}
	}
	
	@SuppressWarnings("unused")
	private static class SubThreadBWait implements Runnable {
		
		private SubThreadAWait threadA;
		
		public SubThreadBWait(SubThreadAWait threadA) {
			this.threadA = threadA;
		}

		public void run() {
			System.out.println("SubThreadBWait start...");
			Long start = System.currentTimeMillis();
			synchronized (threadA) {
				try {
					threadA.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("SubThreadBWait wait:" + (System.currentTimeMillis() - start));
			}
			System.out.println("SubThreadBWait end...");
		}
	}
	
}
