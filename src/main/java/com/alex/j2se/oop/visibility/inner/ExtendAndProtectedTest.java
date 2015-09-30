package com.alex.j2se.oop.visibility.inner;

import com.alex.j2se.oop.visibility.PublicMemVarVisiClass;

public class ExtendAndProtectedTest extends PublicMemVarVisiClass {

	public static void main(String[] args) {
		ExtendAndProtectedTest test = new ExtendAndProtectedTest();
		// 可以访问protected和public权限的成员变量
		System.out.println(test.protectedMem);
		System.out.println(test.publicMem);
	}

}
