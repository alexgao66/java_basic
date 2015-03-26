package com.alex.j2se.thread;

/**
 * 测试使用Flag和interrupt方法中止线程。
 * 
 * @author alex
 * 
 */
public class InterruptTest {

	public static void main(String[] args) {
		System.out.println("Main Thread running......");
		Runnable r = new InterruptThread();
		Thread t = new Thread(r);
		t.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("invoke interrupt method......");
		t.interrupt();
		System.out.println("isInterrupted:" + t.isInterrupted());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("isCancel is true......");
		InterruptThread.isCancel = true;
	}

	protected static class InterruptThread implements Runnable {
		
		public static boolean isCancel = false;

		public void run() {
			System.out.println("InterruptThread running......");
			while (true && !isCancel) {

			}
		}

	}

}
