package com.alex.j2se.enumTest;

/**
 * 测试枚举类型
 * jase5中新增特性
 * @author alex
 *
 */
public class EnumTest {
	public static void main(String[] args){
		// 遍历输出枚举值及顺序
		for(FinalValue v : FinalValue.values()){
			System.out.println(v.toString() + " index:" + v.ordinal());
		}
	}
}


enum FinalValue {
	VALUEA,VALUEB
}
