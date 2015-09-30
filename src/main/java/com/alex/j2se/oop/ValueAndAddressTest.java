package com.alex.j2se.oop;

/**
 * 测试传值和引用之间的差别
 * @author alex
 *
 */
public class ValueAndAddressTest {

	public static void main(String[] args) {
		int i = 2;
		System.out.println(valueTest(i));
		System.out.println(i);
		System.out.println("---------------------------");
		String str = "2";
		System.out.println(strTest(str));
		System.out.println(str);
		System.out.println("---------------------------");
		StringBuilder strBuild = new StringBuilder("2");
		System.out.println(stringBuilderTest(strBuild));
		System.out.println(strBuild);
	}
	
	/**
	 * 测试基础类型是传值的
	 * @param val
	 * @return
	 */
	public static int valueTest(int val) {
		val = val + 1;
		return val;
	}
	
	/**
	 * string是不可变的字符串，所以操作过程中会生成新的string，所以操作之前与之后会不一样
	 * @param str
	 * @return
	 */
	public static String strTest(String str) {
		str = str.concat("1");
		return str;
	}
	
	/**
	 * StringBuilder是可变的字符串，是传引用的操作，所以操作就是对原对象进行的，所以操作前后值是一样的
	 * @param strBuild
	 * @return
	 */
	public static StringBuilder stringBuilderTest(StringBuilder strBuild) {
		return strBuild.append("1");
	}
}
