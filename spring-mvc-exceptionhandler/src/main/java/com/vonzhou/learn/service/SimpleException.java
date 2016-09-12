package com.vonzhou.learn.service;

/**
 * 具体业务对应的异常类
 */
public class SimpleException extends Exception{
    public SimpleException(String msg){
        super(msg);
    }
}
