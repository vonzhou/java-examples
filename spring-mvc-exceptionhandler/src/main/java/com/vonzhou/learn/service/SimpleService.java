package com.vonzhou.learn.service;

import org.springframework.stereotype.Service;

@Service(value = "simpleService")
public class SimpleService {

    public String srv1(String arg) throws Exception{
        if(arg == null || arg.equals("")){
            throw new NullPointerException("SimpleService serv1 参数不能为空");
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
}
