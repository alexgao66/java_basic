package com.alex.j2se.oop;

/**
 * 测试成员变量和局部变量中各类型的默认值
 * 成员变量中：
 * 基本类型，整型值为0，char值为‘’，浮点型为0.0，引用类型值为null
 * 局部变量不管基本类型还是引用类型都必须被初始化
 * @author alex
 *
 */
public class DefaultValueTest {
	
	public byte b;
	
	public short s;
	
	public char c;
	
	public int i;
	
	public long l;
	
	public float f;
	
	public double d;
	
	public String str;
	
	public static void main(String[] args) {
		memberVarDefaultVal();
	}
	
	public static void memberVarDefaultVal() {
		DefaultValueTest vals = new DefaultValueTest();
		System.out.println("byte:" + vals.b);
		System.out.println("short:" + vals.s);
		System.out.println("char:" + vals.c);
		System.out.println("int:" + vals.i);
		System.out.println("long" + vals.l);
		System.out.println("float:" + vals.f);
		System.out.println("double:" + vals.d);
		System.out.println("String:" + vals.str);
	}

}
