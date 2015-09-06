package com.alex.j2se.thread.numCountPool;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 使用线程池，利用多个线程进行任务处理
 *
 * @author gao.jun 
 * @date 2015年9月6日
 *
 */
public class NumbScheduler {
	
	private static ArrayBlockingQueue<CountWorker> works = new ArrayBlockingQueue<CountWorker>(3);
	
	static {
		works.add(new CountWorker("w-1", 0, 0));
		works.add(new CountWorker("w-2", 0, 0));
		works.add(new CountWorker("w-3", 0, 0));
		for(CountWorker w : works) {
			w.start();
		}
	}
	
	public static CountWorker getAvailableWork() throws InterruptedException {
		synchronized (works) {
			CountWorker worker = null;
			while(true) {
				for(CountWorker work : works) {
					if(work.isDone()) {
						worker = work;
						break;
					}
				}
				if(worker == null) {
					System.out.println("Didn't get work,turn into sleep");
					Thread.sleep(500);
				}else {
					System.out.println("Get worker : " + worker.id);
					return worker;
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		getOneMoreWorker();
		ArrayList<CountWorker> myWorks = new ArrayList<CountWorker>();
		for(int i = 1; i <= 3; ++i) {
			myWorks.add(getAvailableWork());
		}
		System.out.println("myWorks size :" + myWorks.size());
		for(CountWorker w : myWorks) {
			w.set(6, 8);
			synchronized (w) {
				w.notify();
			}
		}
	}

	public static void getOneMoreWorker() throws InterruptedException {
		for(int i = 1; i <= 4; ++i) {
			CountWorker w = getAvailableWork();
			w.set(0, 5);
			synchronized (w) {
				w.notify();
			}
		}
	}
}
