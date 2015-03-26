package com.alex.j2se.thread.practise;

/**
 * java编程思想第四版中并发第1题
 * 使用Runnable、Thread、yield方法
 * @author alex
 *
 */
public class Practise1 {

	public static void main(String[] args) {
		for(int i = 1; i <= 10; ++i) {
			new Thread(new ThreeTimeRunner(i)).start();
		}
	}
}

class ThreeTimeRunner implements Runnable {
	
	private int id;
	
	public ThreeTimeRunner(int id) {
		this.id = id;
		System.out.println("id-" + id + " start....");
	}

	public void run() {
		for(int i = 1; i <= 3; i++) {
			System.out.println("id-" + id + " running at " + i + " times");
			Thread.yield();
		}
		System.out.println("id-" + id + " end....");
	}
	
}
