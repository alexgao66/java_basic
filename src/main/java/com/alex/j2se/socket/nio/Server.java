package com.alex.j2se.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocketChannel server = ServerSocketChannel.open();
			server.socket().bind(new InetSocketAddress(9111));
			server.configureBlocking(false);
			Selector selector = Selector.open();
			server.register(selector, SelectionKey.OP_ACCEPT);
			while(true) {
				SocketChannel channel = server.accept();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
