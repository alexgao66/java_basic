package com.alex.jvm;

public class JvmTest {

	public static void main(String[] args) {
		try {
			Thread.currentThread().sleep(10000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
