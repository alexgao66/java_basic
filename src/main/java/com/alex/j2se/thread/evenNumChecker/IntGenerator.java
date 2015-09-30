package com.alex.j2se.thread.evenNumChecker;

/**
 * 偶数生成器抽象类，提供cancel方法用于控制是否终止
 * @author alex
 *
 */
public abstract class IntGenerator {
	/**
	 * 使用volatile控制成员变量的可视性
	 */
	private volatile boolean canceled = false;
	
	public abstract int next();
	
	public void cancel() {
		this.canceled = true;
	}
	
	public boolean isCanceled() {
		return this.canceled;
	}
}
