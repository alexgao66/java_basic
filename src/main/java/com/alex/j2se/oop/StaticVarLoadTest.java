package com.alex.j2se.oop;

import com.alex.j2se.oop.StaticVar;

/**
 * 测试静态常量的加载
 * @author alex
 *
 */
public class StaticVarLoadTest {
	
	public static void main(String[] args) {
		System.out.println("StaticVarLoadTest");
		printStaticVar();
	}
	
	private static void printStaticVar() {
		// 编译期常量可以在类不加载时就可访问，应为存储在JVM方法区（method area）中的常量池中
		System.out.println(StaticVar.FIANL_VAR);
		
		// 虽然是静态常量，但不是编译期常量，仍然会引起类的加载
		System.out.println(StaticVar.FINAL_VAR_INT);
		
		// 访问静态变量时会引起类的加载
		System.out.println(StaticVar.VAR);
	}
	
}
