package com.alex.netty.timeServer;

import java.util.Date;

/**
 * Created by gaojun on 16/2/14.
 */
public class NettyUnixTime {

    private final long value;

    public NettyUnixTime() {
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }

    public NettyUnixTime(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((value() - 2208988800L) * 1000L).toString();
    }

}
