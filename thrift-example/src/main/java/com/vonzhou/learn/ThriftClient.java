package com.vonzhou.learn;

import com.vonzhou.learn.rpc.CalculatorService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @version 2016/10/13.
 */
public class ThriftClient {
    public static void main(String[] args) {
        try {
            TTransport transport = new TSocket("localhost", 9090);
            /**
             *  Opens the transport for reading/writing.
             */
            transport.open();

            /**
             * 指定具体的 protocol
             */
            TProtocol protocol = new TBinaryProtocol(transport);

            /**
             * TServiceClient ：通过 protocol 和 transport 与具体的服务通信，理解为 Stub
             */
            CalculatorService.Client client = new CalculatorService.Client(protocol);

            /**
             * RPC call
             */
            System.out.println(client.add(100, 200));

            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException x) {
            x.printStackTrace();
        }
    }
}
