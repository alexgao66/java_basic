package com.alex.j2se.collection;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 测试deque接口
 * ArrayDeque、LinkedBlockingDeque
 * @author alex
 *
 */
public class DequeTest {

	public static void main(String[] args) {
		arrayDequeTest();
	}
	
	private static void arrayDequeTest() {
		Deque<String> deque = new ArrayDeque<String>();
		deque.add("a");
		deque.add("b");
		deque.add("c");
		System.out.println(deque);
		System.out.println(deque.removeFirst());
		System.out.println(deque.removeLast());
		System.out.println(deque);
		System.out.println("-------模拟栈---------");
		// 模拟栈
		deque.addFirst("a");
		System.out.println(deque);
		System.out.println(deque.removeFirst());
		System.out.println("-------模拟队列---------");
		// 模拟队列
		deque.addLast("c");
		System.out.println(deque);
		System.out.println(deque.removeFirst());
	}
}
