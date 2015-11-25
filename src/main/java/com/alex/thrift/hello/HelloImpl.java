package com.alex.thrift.hello;

import org.apache.thrift.TException;

/**
 * Created by gao.jun on 2015/11/24.
 */
public class HelloImpl implements HelloService.Iface {
    @Override
    public String say(String name) throws TException {
        System.out.println("hi,".concat(name));
        return name;
    }
}
