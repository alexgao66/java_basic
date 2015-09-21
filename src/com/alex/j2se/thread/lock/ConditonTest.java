package com.alex.j2se.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试使用Condition进行线程间的协作
 * @author alex
 *
 */
public class ConditonTest {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Container c = new Container();
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(new PutTask(c));
		es.execute(new TakeTask(c));
		TimeUnit.SECONDS.sleep(10);
		es.shutdownNow();
	}

}

/**
 * 创建一个容器作为共享资源
 * @author alex
 *
 */
class Container {
	private boolean isEmpty = true;
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void put() {
		lock.lock();
		try {
			isEmpty = false;
			condition.signalAll();
		}finally{
			lock.unlock();
		}
	}
	
	public void take() {
		lock.lock();
		try {
			isEmpty = true;
			condition.signalAll();
		}finally{
			lock.unlock();
		}
	}
	
	public void waitForPut() throws InterruptedException {
		lock.lock();
		try{
			while(isEmpty) {
				condition.await();
			}
		}finally{
			lock.unlock();
		}
	}
	
	public void waitForTake() throws InterruptedException {
		lock.lock();
		try{
			while(!isEmpty) {
				condition.await();
			}
		}finally{
			lock.unlock();
		}
	}
}

/**
 * 向容器中增加元素的任务
 * @author alex
 *
 */
class PutTask implements Runnable {
	
	private Container container;
	
	public PutTask(Container container) {
		this.container = container;
	}

	public void run() {
		try {
			while(!Thread.interrupted()) {
				container.put();
				System.out.println("put");
				TimeUnit.SECONDS.sleep(1);
				container.waitForTake();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("PutTask finish...");
	}
}

/**
 * 从容器中取走元素的任务
 * @author alex
 *
 */
class TakeTask implements Runnable {
	
	private Container container;
	
	public TakeTask(Container container) {
		this.container = container;
	}

	public void run() {
		try {
			while(!Thread.interrupted()) {
				container.take();
				System.out.println("Take");
				TimeUnit.SECONDS.sleep(1);
				container.waitForPut();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("TakeTask finish...");
	}
	
}

