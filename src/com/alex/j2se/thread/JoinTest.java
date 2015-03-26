package com.alex.j2se.thread;

/**
 * 测试线程的join方法，实现将将线程的执行顺序化
 * 将子线程加入到main线程，在子线程结束结束之后才进入到主线程
 * @author alex
 *
 */
public class JoinTest {

	public static void main(String[] args) {
		SubThread subTh = new SubThread();
		System.out.println("Start child thread...");
		subTh.start();
		System.out.println("Main thread start to play game...");
		new JoinTest().play();
		try {
			subTh.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Start child finish,main thread start to work...");
	}
	
	public void play(){
		try {
			Thread.sleep(25000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class SubThread extends Thread {

	@Override
	public void run() {
		try {
			for (int i = 0; i < 20; ++i) {
				System.out.println(i);
				Thread.sleep(1000);

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
