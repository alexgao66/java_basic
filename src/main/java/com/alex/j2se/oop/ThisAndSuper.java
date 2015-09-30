package com.alex.j2se.oop;

/**
 * 测试this和super关键字的使用
 * 成员变量之间是可以覆盖的，可以通过super关键字获取父类中的成员变量
 * @author alex
 *
 */
public class ThisAndSuper {

	public static void main(String[] args) {
		BasicClass basic = new BasicClass(1,2);
		System.out.println(basic.memberA);
		System.out.println(basic.memberB);
		System.out.println("-------------------------");
		ExtendClass extend = new ExtendClass(1,2,3);
		System.out.println(extend.memberA);
		System.out.println(extend.getSuperMemberB());
		System.out.println(extend.memberB);
		System.out.println(extend.memberC);
	}

}

class BasicClass {
	
	public int memberA;
	
	public int memberB;
	
	public BasicClass(int memberA,int memberB) {
		this.memberA = memberA;
		this.memberB = memberB;
	}

}

class ExtendClass extends BasicClass {
	
	public int memberB;
	
	public int memberC;

	public ExtendClass(int memberA, int memberB, int memberC) {
		super(memberA, memberB);
		this.memberB = memberC;
		this.memberC = memberC;
	}
	
	/**
	 * 使用super获取父类中的成员变量。
	 * @return
	 */
	public int getSuperMemberB() {
		return super.memberB;
	}
}