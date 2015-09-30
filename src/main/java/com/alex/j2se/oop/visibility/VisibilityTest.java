package com.alex.j2se.oop.visibility;

/**
 * 测试成员变量和成员方法的访问权限
 * java中权限控制的有大到小的顺序是public、protected（继承）、default（包）、private
 * @author alex
 *
 */
public class VisibilityTest {

	public static void main(String[] args) {
		memberVarTest();
	}

	public static void memberVarTest() {
		MemVisibilityClass memVisObj = new MemVisibilityClass();
		System.out.println(memVisObj.publicMem);
		System.out.println(memVisObj.defaultMem);
		System.out.println(memVisObj.protectedMem);
		// 无法访问private成员变量
//		System.out.println(memVisObj.privateMem);
	}
	
	public static void memberMethodTest() {
		MethodVisibilityClass methodVisObj = new MethodVisibilityClass();
		methodVisObj.sayHiInPublic();
		methodVisObj.sayHiInDefault();
		methodVisObj.sayHiInProtected();
		// 无法访问私有成员方法
		//methodVisObj.sayHiInPrivate();
	}

}

/**
 * default权限的类，表示只有包访问权限
 * @author alex
 *
 */
class MemVisibilityClass {
	
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

class MethodVisibilityClass {
	
	public void sayHiInPublic() {
		System.out.println("Say hi in public method");
	}
	
	void sayHiInDefault() {
		System.out.println("Say hi in default method");
	}
	
	protected void sayHiInProtected() {
		System.out.println("Say hi in protected method");
	}
	
	private void sayHiInPrivate() {
		System.out.println("Say hi in private method");
	}
}

