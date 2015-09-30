package com.alex.j2se.oop;


/**
 * 测试方法的重载
 * @author alex
 *
 */
public class OverLoadTest {

	public static void main(String[] args) {
		OverLoadClass obj = new OverLoadClass();
		// 输出1
		System.out.println(obj.process());
		// 输出3
		System.out.println(obj.process(2));
		// 输出5
		System.out.println(obj.process(3L));
		// 输出8
		System.out.println(obj.process(1,2));
		// 输出9
		System.out.println(obj.process(1));
		// 输出11
		System.out.println(obj.process(new Integer(1)));
		
		short s = 1;
		// 输出14
		System.out.println(obj.process(s));
	}

}

class OverLoadClass {
	
	private int member;
	
	public OverLoadClass(){}
	
	public OverLoadClass(int member) {
		this.member =member; 
	}
	
	public int process() {
		return ++this.member;
	}
	
	public int process(int plusVal) {
		this.member += plusVal;
		return this.member;
	}
	
	/**
	 * 不能通过方法访问权限区分重载
	 * @return
	 */
//	int process() {}
	
	/**
	 * 包装类也可以实现重载
	 * @param plusVal
	 * @return
	 */
	public int process(Integer plusVal) {
		this.member += plusVal;
		return ++this.member;
	}
	
	/**
	 * 测试类型提升问题
	 * @param plusVal
	 * @return
	 */
	public int process(short plusVal) {
		this.member += plusVal;
		this.member += 2;
		return this.member;
	}
	
	/**
	 * 参数类型实现重载
	 * @param plusVal
	 * @return
	 */
	public int process(long plusVal) {
		this.member += plusVal;
		return --this.member;
	}
	
	/**
	 * 参数个数实现重载
	 * @param plusVal1
	 * @param plusVal2
	 * @return
	 */
	public int process(int plusVal1, int plusVal2) {
		this.member = this.member + plusVal1 + plusVal2;
		return this.member;
	}
	
	// 无法通过返回类型实现重载
	//public void process(){}
}