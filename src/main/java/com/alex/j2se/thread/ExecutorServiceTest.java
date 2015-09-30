package com.alex.j2se.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 测试ExecutorService
 * @author alex
 *
 */
public class ExecutorServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 最多可创建3个线程，剩余的两个线程在前三个线程结束之后才开始
//		testCachedThreadPool(Executors.newFixedThreadPool(3));
		// 按需创建，不进行限制
		testCachedThreadPool(Executors.newCachedThreadPool());
		// 一次只允许一个线程执行，如创建多个线程会依次执行
//		testCachedThreadPool(Executors.newSingleThreadExecutor());
	}
	
	/**
	 * 测试使用不同类型的ExcutorService进行线程管理
	 * @param service
	 */
	public static void testCachedThreadPool(ExecutorService service){
//		ExecutorService service = Executors.newCachedThreadPool();
		for(Integer i = 0; i < 2; ++i){
			service.execute(new MyRunner(i.toString()));
		}
		// 设置停止状态，不终止在执行状态的线程
		service.shutdown();
		System.out.println("shutdown...");
		// 尝试立刻中断线程，但线程内部如果无法终止，线程依然会正常执行完成。
//		service.shutdownNow();
//		System.out.println("shutdownNow...");
	}
}


class MyRunner implements Runnable {
	
	private String id;
	
	public MyRunner(String id){
		this.id = id;
	}

	public void run() {
		try {
			
			for(Integer i = 0; i < 10; ++i) {
				System.out.println(id.concat("-").concat(i.toString()).concat(" "));
				if(i==9){
					System.out.println("Thread-"+id+" finished...");
				}
				// 设置sleep后，在调用shutdownNow后线程才能立刻终止。
//				Thread.sleep(1000);
				// 使用jdk5版本中提供的工具类TimeUnit
				int sleepInt = new Random(47).nextInt(2000);
				TimeUnit.MILLISECONDS.sleep(sleepInt);
				System.out.println("Sleeped:" + sleepInt);
			}
		} catch (Exception e) {
		}
	}
	
}