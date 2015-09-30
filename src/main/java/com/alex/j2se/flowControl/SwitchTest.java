package com.alex.j2se.flowControl;

/**
 * 测试Switch语句的使用
 * @author alex
 *
 */
public class SwitchTest {

	public static void main(String[] args) {
		intSwitch(2);
		intSwitch(3);
		System.out.println("----------------------");
		charSwitch('b');
		charSwitch('d');
		System.out.println("----------------------");
		enumSwitch(CaseVal.VAL1);
		enumSwitch(CaseVal.VAL3);
	}
	
	/**
	 * 测试在switch中使用int
	 * @param i
	 */
	public static void intSwitch(int i) {
		switch(i) {
			case 1 : 
				System.out.println(1);
				break;
			case 2 :
				System.out.println(2);
				break;
			default :
				System.out.println(0);
		}
	}
	
	/**
	 * 测试在switch中使用character
	 * @param c
	 */
	public static void charSwitch(char c) {
		switch(c) {
			case 'a' :
				System.out.println('a');
				break;
			case 'b' :
				System.out.println('b');
				// 省略break会继续向下执行
//				break;
			default :
				System.out.println('c');
		}
	}
	
	/**
	 * 测试在switch中使用enum
	 * @param val
	 */
	public static void enumSwitch(CaseVal val) {
		switch(val) {
			case VAL1 : 
				System.out.println(val+":"+val.ordinal());
				break;
			case VAL2 :
				System.out.println(val+":"+val.ordinal());
				break;
			default : 
				System.out.println("default:"+-1);
		}
	}
}

enum CaseVal {
	VAL1,VAL2,VAL3
}
