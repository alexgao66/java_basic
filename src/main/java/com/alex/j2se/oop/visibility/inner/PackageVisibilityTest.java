package com.alex.j2se.oop.visibility.inner;

import com.alex.j2se.oop.visibility.PublicMemMethodVisiClass;
import com.alex.j2se.oop.visibility.PublicMemVarVisiClass;

public class PackageVisibilityTest {

	public static void main(String[] args) {
		// default权限的类，在包外部无法访问
//		MemVisibilityClass mvc = new MemVisibilityClass();
	}
	
	public static void memberVarTest() {
		PublicMemVarVisiClass memVisObj = new PublicMemVarVisiClass();
		System.out.println(memVisObj.publicMem);
		// 无法访问default（包）权限的变量
//		System.out.println(memVisObj.defaultMem);
		// 无法访问protected（子类）权限的变量
//		System.out.println(memVisObj.protectedMem);
		// 无法访问private成员变量
//		System.out.println(memVisObj.privateMem);
	}
	
	public static void memberMethodTest() {
		PublicMemMethodVisiClass methodVisObj = new PublicMemMethodVisiClass();
		methodVisObj.sayHiInPublic();
		// 无法访问default（包）权限的方法
//		methodVisObj.sayHiInDefault();
		// 无法访问protected（子类）权限的方法
//		methodVisObj.sayHiInProtected();
		// 无法访问私有成员方法
		//methodVisObj.sayHiInPrivate();
	}

}
