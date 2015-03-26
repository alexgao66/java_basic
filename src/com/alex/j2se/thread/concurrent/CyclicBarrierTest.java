package com.alex.j2se.thread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * 测试用cyclicBarrier实现多个线程执行结果的合并
 * @author alex
 *
 */
public class CyclicBarrierTest {

	public static void main(String[] args) throws InterruptedException {
//		computeFloatVal();
		CyclicBarrierCircle cbc = new CyclicBarrierCircle();
		cbc.startWork();
	}

	public static void computeFloatVal() {
		float[][] data = {{0.1f,0.2f},{0.3f,0.4f},{0.5f,0.5f},
							{0.1f,0.2f},{0.3f,0.4f},{0.5f,0.5f},
							{0.1f,0.2f},{0.3f,0.4f},{0.5f,0.5f},
							{0.1f,0.2f},{0.3f,0.4f},{0.5f,0.5f},
							{0.1f,0.2f},{0.3f,0.4f},{0.5f,0.5f},
							{0.1f,0.2f},{0.3f,0.4f},{0.5f,0.5f},
							{0.1f,0.2f},{0.3f,0.4f},{0.5f,0.5f},
							{0.1f,0.2f},{0.3f,0.4f},{0.5f,0.5f},
							{0.1f,0.2f},{0.3f,0.4f},{0.5f,0.5f},
							{0.1f,0.2f},{0.3f,0.4f},{0.5f,0.5f}};
		new Solver(data);
	}
}

class Solver {
	final int N;
	
	final float[][] data;
	
	final CyclicBarrier cb;
	
	// 存储所有Runnable对象
	List<Worker> workers ;
	
	float value;
	
	public Solver(float[][] matrix) {
		data = matrix;
		N = matrix.length;
		cb = new CyclicBarrier(N,new Runnable(){
			// 最后一个worker执行结束之后会进入以下的合并计算逻辑中。
			public void run() {
				for(Worker woker : workers) {
					value += woker.getResult();
				}
				System.out.println("\nFinal result: "+value+"\n");
			}
		});
		workers = new ArrayList<Worker>();
		 for (int i = 0; i < N; ++i){
			 Worker worker = new Worker(i);
			 workers.add(worker);
			 new Thread(worker).start();
		 } 

	}
	
	/**
	 * 计算每行数字的总和
	 * @author alex
	 *
	 */
	class Worker implements Runnable {
		/**
		 * 行号
		 */
		int rowNum;
		/**
		 * 存储单行数字的总和
		 */
		float result;
		
		public Worker(int rowNum) {
			this.rowNum = rowNum;
		}
		
		public void run() {
			for(int i = 0; i < data[rowNum].length; ++i){
				result += data[rowNum][i];
			}
			System.out.println("rowNum:" + rowNum +",result: " + result);
			try {
				cb.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			// CyclicBarrier中的work执行完成，且回调线程执行结束之后，各个work会继续执行
			System.out.println("rowNum:" + rowNum +" wake up...");
		}
		
		float getResult() {
			return result;
		}
		
	}
	
	float getValue(){
		return this.value;
	}
}

class CyclicBarrierCircle {
	
	final CyclicBarrier cb;
	
	private String val;
	
	private List<Worker> workers;
	
	public CyclicBarrierCircle() {
		val = "";
		cb = new CyclicBarrier(3,new Runnable(){
			public void run() {
					for(Worker w : workers) {
						System.out.println(w.getVal());
						val += w.getVal();
					}
					System.out.println("val:" + val);
					try {
						TimeUnit.SECONDS.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}});
		workers = new ArrayList<Worker>();
		workers.add(new Worker(1,3));
		workers.add(new Worker(4,6));
		workers.add(new Worker(7,9));
	}
	
	public void startWork() {
		ExecutorService es = Executors.newCachedThreadPool();
		for(Worker w : workers) {
			es.execute(w);
//			new Thread(w).start();
		}
		es.shutdown();
	}
	
	
	class Worker implements Runnable {
		
		private int start;
		
		private int end;
		
		private String val;
		
		public Worker(int start,int end) {
			this.start = start;
			this.end = end;
			val = ""; 
		}
		
		public void run() {
			int i = start;
			do{
				this.val += i;
				++i;
			}while(i <= end);
			try {
				cb.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println("finish work: " + start + "-" + end);
		}
		
		public String getVal() {
			return val;
		}
	}
}