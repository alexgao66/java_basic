package com.alex.j2se.oop.visibility;

/**
 * public权限的成员变量访问测试类
 * @author alex
 *
 */
public class PublicMemVarVisiClass {
	
	/**
	 * public成员，通用权限
	 */
	public int  publicMem;
	
	/**
	 * default成员，包访问权限
	 */
	int defaultMem;
	
	/**
	 * protected成员，子类访问权限
	 */
	protected int protectedMem;
	
	/**
	 * private成员，类内部访问权限
	 */
	private int privateMem;
}
