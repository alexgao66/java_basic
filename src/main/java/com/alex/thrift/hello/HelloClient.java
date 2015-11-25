package com.alex.thrift.hello;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by gao.jun on 2015/11/24.
 */
public class HelloClient {

    public void startClient() {
        TTransport transport;
        try {
            System.out.println("Client connect server at 12345");
            transport = new TSocket("localhost", 12345);
            TProtocol protocol = new TBinaryProtocol(transport);
            HelloService.Client client = new HelloService.Client(protocol);
            transport.open();
            client.say("高俊");
            transport.close();
            System.out.println("Client close connection");
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Thrift client init.");
        HelloClient client = new HelloClient();
        client.startClient();
        System.out.println("Thrift client close.");
    }
}
