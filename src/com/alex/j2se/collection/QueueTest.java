package com.alex.j2se.collection;

import static java.lang.System.out;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 测试queue，包括PriorityQueue、PriorityBlockingQueue、SynchronousQueue
 * ConcurrentLinkedQueue、ArrayBlockingQueue、LinkedBlockingQueue、DelayQueue、
 * @author alex
 *
 */
public class QueueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		arrayQueueTest();
	}
	
	/**
	 * 测试queue的常用方法
	 */
	private static void arrayQueueTest() {
		Queue<String> queue = new PriorityQueue<String>();
		queue.add("a");
		queue.add("b");
		out.println(queue);
		out.println(queue.remove());
		out.println(queue);
		queue.add("c");
		out.println(queue);
		out.println(queue.peek());
		queue.offer("d");
		out.println(queue);
	}
}
