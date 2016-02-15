package com.alex.netty.timeServer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by gaojun on 16/2/14.
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyUnixTime m = (NettyUnixTime) msg;
        System.out.println(m);
        ctx.close();
    }
}
