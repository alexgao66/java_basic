package com.alex.j2se.oop;

/**
 * 测试基本类型常量和引用类型常量之间的区别
 * @author alex
 */
public class FinalVarTest {
	
	public static void main(String[] args) {
//		final int finalOrginalVar = 1;
		// 基本类型常量值无法修改
//		finalOrginalVar = 2;
		
		// 引用类型常量的值可以修改，这里的常量是指不能再指向其他变量
		final StringBuffer finalReferVar = new StringBuffer("12345");
		finalReferVar.append("6");
		System.out.println(finalReferVar);
		// 引用类型常量不能被再次赋值
//		finalReferVar = new StringBuffer("123456");
		
		// 包装类也一样
//		final Integer finalInteger = 1;
//		finalInteger = 2;
	}
	
}

