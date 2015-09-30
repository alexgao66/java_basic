package com.alex.j2se.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 测试java中的反射功能
 * @author alex
 *
 */
public class ReflectTest {
	
	private String name;
	
	private int age;
	
	public ReflectTest(){}
	
	public ReflectTest(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	
	public static void main(String[] args){
		getClassByForName();
		System.out.println("--------------");
		getClassByAttribute();
		System.out.println("--------------");
		genericClass();
	}
	
	/**
	 * 通过反射获取类的get方法
	 * @param args
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void reflectMethod() throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		ReflectTest f1 = new ReflectTest("alex",1);
		@SuppressWarnings("all")
		Method m1 = f1.getClass().getMethod("getAge", null);
		@SuppressWarnings("all")
		Object res = m1.invoke(f1, null);
		System.out.println(res);
	}
	
	/**
	 * Class的forName方法会引起类的加载
	 */
	public static void getClassByForName() {
		try {
			Class<?> c = Class.forName("com.alex.j2se.reflect.ClassTest");
			System.out.println(c);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过类字面常量的方式，不会引起类的加载
	 */
	public static void getClassByAttribute() {
		Class<?> c = ClassTest.class;
		System.out.println(c);
	}
	
	/**
	 * 使用泛化Class对象
	 */
	public static void genericClass() {
		Class<? extends ClassTest> c = ClassTest.class;
		System.out.println(c);
		c = SubClassTest.class;
		System.out.println(c);
		// 无法做非类型配置的赋值
		//c = Integer.class;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

class ClassTest {
	static {
		System.out.println("print");
	}
}

class SubClassTest extends ClassTest {
	static {
		System.out.println("print in sub");
	}
}
