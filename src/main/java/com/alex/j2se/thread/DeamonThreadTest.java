package com.alex.j2se.thread;

import java.util.concurrent.TimeUnit;

/**
 * 测试守后台线程
 * 主线程结束结束之后，后台线程立刻停止
 * @author alex
 *
 */
public class DeamonThreadTest {

	public static void main(String[] args) {
		MainThread mt = new MainThread();
		Thread mainThread = new Thread(mt);
		
		mainThread.start();
	}

}

class MainThread implements Runnable {
	
	private Thread dt;
	
	public void run() {
		dt = new Thread(new DaemonThread());
		dt.setDaemon(true);
		dt.start();
		for(int i = 0; i < 5; ++i){
			System.out.println("main: " + i);
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Thread getDt(){
		return dt;
	} 
}

class DaemonThread implements Runnable {

	public void run() {
		for(int i = 0; i < 10; ++i){
			System.out.println("daemon: " + i);
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
