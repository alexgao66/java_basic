package com.alex.j2se.orginalType;

/**
 * 测试java中的基础类
 * @author alex
 *
 */
public class OrginalTypeTest {

	public static void main(String[] args) {
		orginalTypeSize();
		System.out.println("----------------------------------");
		upperType();
		System.out.println("----------------------------------");
		roundValue();
	}
	
	/**
	 * 输出基础类型的长度
	 * 基础类型的长度是固定的，为跨平台特性提供了基础
	 */
	public static void orginalTypeSize() {
		// 整型
		System.out.println("Byte:" + Byte.SIZE);// 8位
		System.out.println("Short:" + Short.SIZE);// 16位
		System.out.println("Character:" + Character.SIZE);// 16位
		System.out.println("Integer:" + Integer.SIZE);// 32位
		System.out.println("Long:" + Long.SIZE);// 64位
		// 浮点型
		System.out.println("Float:" + Float.SIZE);// 32位
		System.out.println("Double:" + Double.SIZE);// 64位
		// 布尔型没有size属性，在jvm中是使用由integer实现的，Boolean数组是有character数组实现的
	}
	
	/**
	 * 测试byte、short、char在运算过程中会提升为int参与计算
	 */
	public static void upperType() {
		byte b = 127;
		short s = 127;
		char c = 'c';
		System.out.println(c);
		System.out.println((int)c);
		int i = b + s + c;
		System.out.println(i);
		char ch = (char)(b + s + c);
		System.out.println(ch);
	}
	
	/**
	 * 测试浮点型数组的截取
	 */
	public static void roundValue() {
		float f1 = 12.345f,f2 = 12.8f;
		// 浮点转整形，自动截取
		System.out.println((int)f1);
		// 使用round方法会进行四舍五入
		System.out.println(Math.round(f1));
		System.out.println(Math.round(f2));
	}
}
