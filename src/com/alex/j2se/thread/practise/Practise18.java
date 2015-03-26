package com.alex.j2se.thread.practise;

/**
 * java编程思想第四版中并发第18题
 * 练习interrupt方法的使用
 * @author alex
 *
 */
public class Practise18 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		practise18();
	}

	public static void practise18() {
		Thread thread = new Thread(new Task(new SleepObj()));
		thread.start();
		thread.interrupt();
	}
	
}

class SleepObj {
	
	public void SleepLong() throws InterruptedException {
		Thread.sleep(5000);
	}
}

class Task implements Runnable {
	
	private SleepObj sleepObj;
	
	public Task(SleepObj sleepObj) {
		this.sleepObj = sleepObj;
	}

	public void run() {
		try {
			sleepObj.SleepLong();
		} catch (InterruptedException e) {
			System.out.println(e);
		}finally {
			System.out.println("Clean all things in finally...");
		}
	}
	
}