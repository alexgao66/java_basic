package com.alex.j2se.socket.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		new Server().start();
	}
	
	public void start() throws IOException {
		ServerSocket server = new ServerSocket(9000);
		System.out.println("server start...");
		while(true) {
			final Socket socket = server.accept();
			new Thread(new Runnable(){

				@Override
				public void run() {
					BufferedReader in = null;
					try {
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//						byte[] buf = new byte[1024];
						String message = in.readLine();
						while(message != null) {
							System.out.println("receive msg:" + message);
							message = in.readLine();
						}
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if(in != null) {
							try {
								in.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
				
			}).start();
		}
	}
}
