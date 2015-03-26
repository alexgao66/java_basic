package com.alex.j2se.oop;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试动态参数
 * 定义Object类型的动态参数，可以多种类型的参数
 * @author alex
 */
public class DynamicArgs {

	public static void main(String[] args) {
		dynamicArgs("测试"); // 单个测试
		dynamicArgs(1,2,3); // 基础类型
		dynamicArgs("123","456","789");  // 引用类型
		dynamicArgs((Object[])new String[]{"a","b"}); // 数组
		List<Double> argList = new ArrayList<Double>(); 
		argList.add(12.1);
		argList.add(13.1);
		dynamicArgs(argList); // 集合
		dynamicArgs(); // 无参
	}
	
	/**
	 * 动态参数
	 * @param args
	 */
	private static void dynamicArgs(Object... args){
		for(Object obj : args){
			System.out.println(obj);
		}
		System.out.println("--------------------------------------");
	}
}
