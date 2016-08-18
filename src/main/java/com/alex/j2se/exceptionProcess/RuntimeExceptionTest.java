package com.alex.j2se.exceptionProcess;

/**
 * 测试自定义RuntimeException
 * RuntimeException不需要抛出（throws），自然也就不需要捕获
 * RuntimeException是一种无法恢复的异常，因此出现RuntimeException是程序就会终止，所以在事务处理中对RuntimeException会进行回滚（rollback）操作
 * @author alex
 *
 */
public class RuntimeExceptionTest {
	
	public static void main(String[] args) {
		try {
			System.out.println(process(0));
		}/*catch (MyRuntimeException myException){
			System.out.println("MyRuntimeException");
		}*/catch (Exception e) {
			System.out.println("Exception");
		}


	}
	
	public static int process(int i) {
		int val = 1;
		UseMyRuntimeException obj = new UseMyRuntimeException();
		obj.process(new String[]{});
		return val;
	}
}

class UseMyRuntimeException {
	
	public void process(String[] args) {
		if(args.length == 0) {
			throw new MyRuntimeException("参数列表为空！");
		}
	} 
}


class MyRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 733416104755781275L;
	
	public MyRuntimeException(String msg,Throwable e) {
		super(msg, e);
	}
	
	public MyRuntimeException(String msg) {
		super(msg);
	}
}