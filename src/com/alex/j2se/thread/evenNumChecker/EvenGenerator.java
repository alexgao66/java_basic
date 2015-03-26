package com.alex.j2se.thread.evenNumChecker;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 偶数生成器具体类
 * 使用Lock或synchronized控制偶数数值的递增。
 * @author alex
 *
 */
public class EvenGenerator extends IntGenerator {
	
	private int currentEvenVal = 0;
	
	private Lock lock = new ReentrantLock();

	@Override
	public /*synchronized*/ int next() {
		lock.lock();
		try{
			++this.currentEvenVal;
			Thread.yield();
			++this.currentEvenVal;
			return this.currentEvenVal;
		} finally {
			lock.unlock();
		}
		
	}
	
	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator());
	}

}
