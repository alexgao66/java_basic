package com.alex.j2se.collection;

import java.util.Arrays;
import java.util.List;

public class ArraysTest {

	public static void main(String[] args) {
		
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
}
