package com.alex.j2se.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ArraysTest {

	public static void main(String[] args) {
		List<Integer> intList = addToList();
//		testRemoveByIndex(intList);
		System.out.println("after init:");
		printArray(intList);
		testRemoveByIterator(intList);
		System.out.println("after remove:");
		printArray(intList);
	}

	private static List<Integer> addToList() {
		List<Integer> intList = new ArrayList<Integer>();
		for(int i = 0; i < 10; ++i) {
			intList.add(i * 2);
		}
		System.out.println("size:" + intList.size());
		return intList;
	}
	
	/**
	 * 使用Arrays.asList将数组转换为List，但是不能使用List中接口方法。
	 * 如使用add或addAll方法会抛出java.lang.UnsupportedOperationException异常
	 */
	public static void asListTest() {
		
		List<Integer> intList = Arrays.asList(12,13);
		
		Integer[] ints = {13,14};
		
		intList.addAll(Arrays.asList(ints));
	
		System.out.println(intList);
	}
	
	/**
	 * 打印输出数组
	 * @param intList
	 */
	public static void printArray(List<Integer> intList) {
		for(int i = 0; i < intList.size(); ++i) {
			System.out.println(intList.get(i));
		}
	}
	
	/**
	 * 通过下标删除元素，报错
	 * @param intList
	 */
	public static void testRemoveByIndex(List<Integer> intList) {
		for(int i = 0, len = intList.size(); i < len; ++i) {
			if(intList.get(i) % 4 == 0) {
				intList.remove(i);
			}
		}
	}
	
	/**
	 * 通过iterator删除元素，Iteror内部使用变量来存储iterator当前的位置
	 * @param intList
	 */
	public static void testRemoveByIterator(List<Integer> intList) {
		for(Iterator<Integer> it = intList.iterator(); it.hasNext(); it.next()){
			if(it.next() % 4 == 0) {
				it.remove();
			}
		}
	}
}
