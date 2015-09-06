package com.alex.j2se.thread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConutDownLatchTest {
	
	public static void main(String[] args) {
		ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(1);
		Sechduler s = new Sechduler();
		scheduExec.scheduleWithFixedDelay(s, 0, 5, TimeUnit.SECONDS);
		try {
			Thread.sleep(20000);
			scheduExec.shutdownNow();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			System.out.println("------------");
		}
	}
	
	static class Sechduler implements Runnable {
		
		List<Worker> workers = new ArrayList<Worker>();
		
		ExecutorService service = Executors.newFixedThreadPool(3);
		
		{
			workers.add(new Worker());
			workers.add(new Worker());
			workers.add(new Worker());
		}
		
		@Override
		public void run() {
			CountDownLatch startLatch = new CountDownLatch(3);
			CountDownLatch endLatch = new CountDownLatch(3);
			
			/*Worker w1 = new Worker(startLatch, endLatch, 1, 3);
			Worker w2 = new Worker(startLatch, endLatch, 4, 6);
			Worker w3 = new Worker(startLatch, endLatch, 7, 9);
			
			service.execute(w1);
			startLatch.countDown();
			service.execute(w2);
			startLatch.countDown();
			service.execute(w3);
			startLatch.countDown();*/
			
			service = Executors.newFixedThreadPool(3);
			
			workers.set(0, workers.get(0).setStartLatch(startLatch).setEndLatch(endLatch).setStart(1).setEnd(3));
			workers.set(1, workers.get(1).setStartLatch(startLatch).setEndLatch(endLatch).setStart(4).setEnd(6));
			workers.set(2, workers.get(2).setStartLatch(startLatch).setEndLatch(endLatch).setStart(7).setEnd(9));
			
			service.execute(workers.get(0));
			startLatch.countDown();
			service.execute(workers.get(1));
			startLatch.countDown();
			service.execute(workers.get(2));
			startLatch.countDown();
			System.out.println("start");
			try {
				endLatch.await();
				System.out.println("end");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				service.shutdownNow();
			}
		}
		
	}
	
	static class Worker implements Runnable {
		
		CountDownLatch startLatch;
		
		CountDownLatch endLatch;
		
		private int start;
		
		private int end;
		
		public Worker() {
		}
		
		public Worker(CountDownLatch startLatch, CountDownLatch endLatch, int start, int end) {
			this.startLatch = startLatch;
			this.endLatch = endLatch;
			this.start = start;
			this.end = end;
		}

		@Override
		public void run() {
			try {
				startLatch.await();
				for(int i = start; i <= end; ++i) {
					System.out.println(i);
					Thread.sleep(1000);
				}
				endLatch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public CountDownLatch getStartLatch() {
			return startLatch;
		}

		public Worker setStartLatch(CountDownLatch startLatch) {
			this.startLatch = startLatch;
			return this;
		}

		public int getStart() {
			return start;
		}

		public Worker setStart(int start) {
			this.start = start;
			return this;
		}

		public CountDownLatch getEndLatch() {
			return endLatch;
		}

		public Worker setEndLatch(CountDownLatch endLatch) {
			this.endLatch = endLatch;
			return this;
		}

		public int getEnd() {
			return end;
		}

		public Worker setEnd(int end) {
			this.end = end;
			return this;
		}
		
	}
}
