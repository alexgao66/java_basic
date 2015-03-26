package com.alex.j2se.exceptionProcess;

/**
 * 测试finally语句在return、exit时的执行情况
 * @author alex
 *
 */
public class FinallyAfterReturn {
	
	public static void main(String[] args) {
		returnIntry();
		exitInTry();
	}
	
	/**
	 * 在try中return后依然会执行finally语句
	 */
	private static void returnIntry() {
		try {
			System.out.println("I'm in try of returnIntry!");
			return;
		} catch(Exception e) {
		} finally {
			System.out.println("I'm in finally of returnIntry!");
		}
	}
	
	private static void exitInTry() {
		try {
			System.out.println("I'm in try of exitInTry!");
			System.exit(-1);
		} catch(Exception e) {
		} finally {
			System.out.println("I'm in finally of exitInTry!");
		}
	}
}
