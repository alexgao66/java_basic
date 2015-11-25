package com.alex.thrift.hello;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by gao.jun on 2015/11/24.
 */
public class HelloServer {

    public void startServer() {
        try {
            System.out.println("Thrift start at 12345");
            TServerSocket serverSocket = new TServerSocket(12345);
            HelloService.Processor processor = new HelloService.Processor(new HelloImpl());
            TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory(true,true);
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverSocket);
            args.processor(processor);
            args.protocolFactory(factory);
            TServer server = new TThreadPoolServer(args);
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        HelloServer server = new HelloServer();
        server.startServer();
        System.out.println("Thrift init...");
    }
}
