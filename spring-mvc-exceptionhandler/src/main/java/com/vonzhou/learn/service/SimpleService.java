package com.vonzhou.learn.service;

import org.springframework.stereotype.Service;

@Service(value = "simpleService")
public class SimpleService {

    public String srv1(String arg) throws Exception{
        if(arg == null || arg.equals("")){
            throw new NullPointerException("SimpleService serv1 : The arg cannot be null");
        }

        /**
         * 具体服务逻辑，如DB
         */

        if(arg.equals("error")){
            String msg = "SimpleService srv1 Failed!";
            System.out.println("Log: " + msg);
            throw new SimpleException(msg);
        }

        /**
         * 返回正确结果
         */

        return "Right Data";
    }

    public String srv2(long id) throws Exception{
        if(id == 1){
            throw new OrderNotFoundException("Not Found Order");
        }

        return " srv2 result";
    }
}
