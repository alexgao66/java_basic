package com.alex.j2se.oop.visibility;

/**
 * public权限的成员方法访问测试类
 * @author alex
 *
 */
public class PublicMemMethodVisiClass {
	
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
