package com.alex.j2se.operator;

/**
 * 测试移位运算符
 * @author alex
 *
 */
public class ByteMoveTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 8,j = 3;
		System.out.println(i >> 1);
		System.out.println(i << 1);
		// 移出位会舍弃
		System.out.println(j >> 1);
		System.out.println(j << 1);
	}

}
