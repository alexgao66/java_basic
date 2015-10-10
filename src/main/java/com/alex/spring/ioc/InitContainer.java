package com.alex.spring.ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitContainer {
	
	public static final void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-base.xml");
		Thread.sleep(1000 * 60 * 10);
	}
	
}
