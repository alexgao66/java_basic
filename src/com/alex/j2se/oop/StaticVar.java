package com.alex.j2se.oop;

import java.util.Random;

/**
 * 定义静态变量和静态常量，用于区分两者在加载过程中的差别
 * @author alex
 *
 */
public class StaticVar {
	static {
		System.out.println("Class4Load loaded...");
	}
	
	public static String VAR = "VAR_In_LOAD";
	
	public static final String FIANL_VAR = "FIANL_VAR_In_LOAD";
	
	public static final int FINAL_VAR_INT = new Random(47).nextInt();
}
