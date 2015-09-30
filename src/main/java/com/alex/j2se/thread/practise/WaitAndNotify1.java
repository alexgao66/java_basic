package com.alex.j2se.thread.practise;

/**
 * 测试wait和notify方法
 * 让一个线程启动后wait，然后让另一个线程唤醒这个线程。
 * @author alex
 *
 */
public class WaitAndNotify1 {
	
	
	public static void main(String[] args) {
		SubThreadA r = new SubThreadA();
		Thread t = new Thread(r);
		t.start();
		new Thread(new SubThreadB(r)).start();
	}
	
	private static class SubThreadA implements Runnable {

		public void run() {
			System.out.println("SubThreadA start...");
			long start = System.currentTimeMillis();
			// 同步是关键
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Wait time:" + (System.currentTimeMillis() - start));
			System.out.println("SubThreadA end...");
		}
	}
	
	private static class SubThreadB implements Runnable {
		
		private SubThreadA ta;
		
		public SubThreadB(SubThreadA ta) {
			this.ta = ta;
		}

		public void run() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 同步时关键
			synchronized (ta) {
				ta.notify();
			}
		}
	}
	
}
