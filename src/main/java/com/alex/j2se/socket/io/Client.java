package com.alex.j2se.socket.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		Socket socket = null;
		PrintWriter out = null;
		try {
			socket = new Socket("localhost", 9000);
			out = new PrintWriter(socket.getOutputStream());
			for(int i = 0; i < 10; ++i) {
				out.print("abc\n");
				out.flush();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				out.close();
			}
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
