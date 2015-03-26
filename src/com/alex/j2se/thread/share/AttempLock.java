package com.alex.j2se.thread.share;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试使用ReentrantLock进行主线程和后台线程之间的同步
 * @author alex
 *
 */
public class AttempLock {
	private ReentrantLock lock = new ReentrantLock();
	
	public void untimed() {
		boolean captured = lock.tryLock();
		try {
			System.out.println("tryLock(): " + captured);
		}finally {
			if(captured) {
				lock.unlock();
			}
		}
	}
	
	public void timed() {
		boolean captured = false;
		try {
			captured = lock.tryLock(2,TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		try{
			System.out.println("lock.tryLock(2,TimeUnit.SECONDS): " + captured);
		}finally {
			if(captured) {
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		final AttempLock al = new AttempLock();
		al.untimed();
		al.timed();
//		al.lock.lock();
		new Thread(){
			{this.setDaemon(true);}
			public void run() {
				al.lock.lock();
				System.out.println("acquired");
			}
		}.start();
//		al.lock.unlock();
		Thread.yield();
		al.untimed();
		al.timed();
	}
}
