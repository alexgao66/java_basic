package com.alex.j2se.thread.numCountPool;

import java.util.concurrent.ArrayBlockingQueue;


public class CountWorker extends Thread {
	
	private Boolean done = true;
	
	public String id;
	
	private int start;
	
	private int end;
	
	private ArrayBlockingQueue<CountWorker> queue;
	
	public CountWorker(String id, int start, int end) {
		this.id = id;
		this.start = start;
		this.end = end;
	}

	@Override
	public void run() {
		while(true) {
			synchronized (this) {
				if(done) {
					try {
						System.out.println(id + " turn into wait");
						queue.add(this);
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(id + " start work");
				while(start < end) {
					++start;
					System.out.println(id + " work at " + start + ",turn into sleep");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				done = true;
			}
		}
	}

	public boolean isDone() {
		return done;
	}
	
	public void set(int start, int end) {
		this.start = start;
		this.end = end;
		done = false;
	}
}
