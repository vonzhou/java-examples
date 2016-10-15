package com.vonzhou.learn;

import com.vonzhou.learn.rpc.CalculatorService;
import com.vonzhou.learn.rpc.CalculatorServiceHandler;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * @version 2016/10/13.
 */
public class ThriftServer {
    public static void start(CalculatorService.Processor<CalculatorServiceHandler> processor) {
        try {
            /**
             * 服务器端传输层初始化
             */
            TServerTransport serverTransport = new TServerSocket(9090);
            /**
             * Thrift Server 这里采用简单单线程实现， 用于测试环境
             */
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
            System.out.println("Starting the simple thrift server...");
            /**
             * 启动服务
             */
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        start(new CalculatorService.Processor<CalculatorServiceHandler>(new CalculatorServiceHandler()));
    }
}
