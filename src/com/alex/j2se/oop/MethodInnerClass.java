package com.alex.j2se.oop;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试方法中的内部类
 * @author alex
 *
 */
public class MethodInnerClass {
	
	private List<Integer> intList;
	
	public MethodInnerClass(List<Integer> intList) {
		this.intList = intList;
	}

	public void process(Processable<Integer> processor) {
		processor.process(intList);
	}
	
	public void printList() {
		System.out.println(intList);
	}
	
	/**
	 * 在方法内部定义实名的内部类
	 */
	public static void defaultProcess() {
		class DefaultProcessor implements Processable<Integer> {
			public void process(List<Integer> list) {
				list.set(1, 1);
			}
			public void process() {
			}
		}
		
		final List<Integer> intList = new ArrayList<Integer>();
		intList.add(1);
		intList.add(2);
		intList.add(3);
		
		MethodInnerClass mic = new MethodInnerClass(intList);
		mic.printList();
		mic.process(new DefaultProcessor());
		mic.printList();
	} 
	
	/**
	 * 在方法内部定义匿名内部类
	 */
	public static void anonyInnerClassMethod() {
		final List<Integer> intList = new ArrayList<Integer>();
		intList.add(1);
		intList.add(2);
		intList.add(3);
		
		final String str = "abc";
		
		final int i = 1;
		
		MethodInnerClass mic = new MethodInnerClass(intList);
		mic.printList();
		mic.process(new Processable<Integer>() {
			public void process(List<Integer> list) {
				list.set(0, 0);
			}
			/**
			 * 方法中的内部类可以引用方法中的常量
			 */
			public void process() {
				intList.set(1, 1);
				System.out.println(str);
				System.out.println(i);
			}
		});
		mic.printList();
	}
	
	public static void main(String[] args) {
		defaultProcess();
		
	}
}
