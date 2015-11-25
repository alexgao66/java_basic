package com.alex.j2se.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketBasicTest {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("www.letvcloud.com", 80);
			System.out.println(socket.getLocalPort());
			System.out.println(socket.getLocalAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
