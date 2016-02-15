package com.alex.netty.echoServer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {

	public void start() throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		
		b.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress("localhost", 65535))
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new EchoClientHandler());
				}
		});
		ChannelFuture f = b.connect().sync();
		Channel chanel = f.channel();
		for(int i = 0; i < 10; ++i) {
			chanel.writeAndFlush(i);
		}
		chanel.closeFuture().sync();
	}
	
	public static void main(String[] args) throws InterruptedException {
		new EchoClient().start();
	}
}
