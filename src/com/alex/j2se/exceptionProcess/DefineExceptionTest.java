package com.alex.j2se.exceptionProcess;

/**
 * 测试自定义Exception的处理逻辑
 * Exception类型的异常是一种可恢复性的异常，为了使程序执行可恢复，所以需要使用try...catch...finally从异常中恢复执行
 * @author alex
 *
 */
public class DefineExceptionTest {

	public static void main(String[] args) {
		System.out.println("处理之后的值：" + process());
	}
	
	public static int process() {
		int value = 1;
		UseMyException obj = new UseMyException();
		String arg = null;
		try {
			obj.process(arg);
		} catch (MyException e) {
			e.printStackTrace();
		} finally {
			value -= 1;
		}
		return value;
	}

}

class MyException extends Exception {
	
	private static final long serialVersionUID = -1549946912714566301L;

	public MyException(String msg,Throwable e) {
		super(msg, e);
	}
	
	public MyException(String msg) {
		super(msg);
	}
}

class UseMyException {
	
	public void process(String args) throws MyException {
		if(args == null) {
			throw new MyException("参数为空！");
		}
	}
}