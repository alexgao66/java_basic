package com.alex.j2se.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 测试list接口，包括ArrayList，LinkedList，Vector，CopyOnWriteArrayList
 * ArrayQueue
 * @author alex
 *
 */
public class ListTest {
	public static void main(String[] args){
//		arrayListTest();
//		copyOnWriteArrayListTest();
		listToSet();
	}
	
	/**
	 * 测试 arraylist，使用数组实现，非线程安全
	 */
	@SuppressWarnings("unused")
	private static void arrayListTest() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		
		System.out.println(list);
		
		Iterator<String> it = list.iterator();
		if(it.hasNext()) {
			it.next();
			it.remove();
		}
		System.out.println(list);
	}
	
	/**
	 * 测试arraylist，双向循环链表
	 */
	@SuppressWarnings("unused")
	private static void linkedListTest() {
		LinkedList<String> list = new LinkedList<String>();
		
		list.add("a");
		list.add("b");
		list.add("c");
		
		System.out.println(list);
	}
	
	/**
	 * 测试vector，线程安全，使用数组实现
	 */
	@SuppressWarnings("unused")
	private static void vectorTest() {
		Vector<String> v = new Vector<String>();
		v.add("a");
		v.add("b");
		v.add("c");
		System.out.println(v);
	}
	
	/**
	 * 测试copyOnWriteArrayList
	 * CopyOnWriteArrayList在写入（add,set,remove）时使用Lock对象加锁并对整个数组进行复制，读时不加锁。
	 * 适合读多写少的场景。
	 * “快照”风格的迭代器，不能使用迭代器对元素进行修改。
	 */
	@SuppressWarnings("unused")
	private static void copyOnWriteArrayListTest(){
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		
		System.out.println(list);
		
		list.set(2, "d");
		System.out.println(list);
		
		Iterator<String> it = list.iterator();
		if(it.hasNext()) {
			it.next();
			it.remove();
		}
		System.out.println(list);
	}
	
	/**
	 * 测试addAll方法
	 * 抛出java.lang.UnsupportedOperationException异常，因为List使用Array构造。
	 */
	@SuppressWarnings("unused")

	private static void arrayAsListTest() {
				
		List<Integer> intList = Arrays.asList(12,13);
		
		Integer[] ints = {13,14};
		
		intList.addAll(Arrays.asList(ints));
	
		System.out.println(intList);
	}
	
	/**
	 * 测试LinkedList中比较常用的方法
	 */
	@SuppressWarnings("unused")
	private static void commonMethodTest() {
		LinkedList<String> list = new LinkedList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		System.out.println(list);
		// 使用下标 b
		System.out.println("get(1): " + list.get(1));
		// 获取第一个元素 a
		System.out.println("getFirst: " + list.getFirst());
		// 获取最后一位元素 c
		System.out.println("getLast: " + list.getLast());
		// 是否为空 false
		System.out.println("isEmpty: " + list.isEmpty());
		// set修改时返回原值 c
		System.out.println("set(2, \"d\"): " + list.set(2, "d"));
		// set修改后 [a,b,d]
		System.out.println("after set(2, \"d\"): " + list);
		// 使用iterator 实际是在AbstarctList中实现的，只可以先后遍历。
		System.out.println("iterator: ");
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		// 使用ListIterator可以向前、向后遍历
		ListIterator<String> listIt = list.listIterator();
		// 判断前面是否还有元素 false
		System.out.println("ListIterator hasPrevious: " + listIt.hasPrevious());
		// 获取游标位置 -1
		System.out.println("ListIterator previousIndex: " + listIt.previousIndex());
		// 获取下一个元素 a
		System.out.println("ListIterator next: " + listIt.next());
		listIt.next();
		// 执行两次next之后获取前一个元素b
		System.out.println("ListIterator previous: " + listIt.previous());
	}
	
	/**
	 * 测试Stack，线程安全的，后进先出，继承至vector。
	 * 优先使用deque接口下的类。
	 * @author alex
	 */
	public static void stackTest() {
		Stack<String> stack = new Stack<String>();
		stack.push("abc");
		stack.push("def");
		stack.push("ghi");
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
	
	/**
	 * 测试List集合的删除操作
	 */
	public static void testRemove() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		removeWithForeach(list);
	}
	
	/**
	 * 当使用size配合get方法做删除操作时，会报下标越界错误
	 * @param list
	 */
	public static void removeWithSize(ArrayList<Integer> list) {
		for(int i = 0,size = list.size(); i < size; ++i) {
			if(list.get(i) % 2 == 0) {
				list.remove(i);
			}
		}
	}
	
	/**
	 * 当使用foreach的方式做删除操作时，会报并发修改错误
	 * @param list
	 */
	public static void removeWithForeach(ArrayList<Integer> list) {
		for(Integer i : list) {
			if(i % 2  == 0) {
				list.remove(i);
			}
		}
	}
	
	/**
	 * 测试将List转化为Set，List中重复的元素不会出现在Set中
	 * 可以直接使用Set的构造方法，以List为参数创建Set
	 */
	public static void listToSet() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(3);
		Set<Integer> set = new HashSet<Integer>(list);
		System.out.println("size:" + set.size() + " element:");
		for(Integer val : set) {
			System.out.println(val);
		}
	}
}
