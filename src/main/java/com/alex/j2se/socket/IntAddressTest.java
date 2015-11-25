package com.alex.j2se.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IntAddressTest {

	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getByName("blog.sina.com.cn");
			System.out.println(address);
			System.out.println("___________________________");
			InetAddress[] addresses = InetAddress.getAllByName("www.letvcloud.com");
			for(InetAddress addr :  addresses) {
				System.out.println(addr);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
