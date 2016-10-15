package com.vonzhou.learn.thread;

/**
 * @version 2016/10/11.
 */
public class Message {
    private String msg;

    public Message(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
