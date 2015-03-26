package com.alex.j2se.oop;

/**
 * 测试动态参数列表
 * jase5中新增特性
 * @author alex
 *
 */
public class DynamicParaListTest {

	public static void main(String[] args) {
		// 参数列表可以为null
		methodChar();
		methodChar('a','b','c');
		methodInt(1,2,3,'a');
	}
	
	public static void methodChar(Character... chars) {
		System.out.println("char length: " + chars.length);
	}
	
	public static void methodInt(int... ints) {
		System.out.println("integer length: " + ints.length);
	}
}
