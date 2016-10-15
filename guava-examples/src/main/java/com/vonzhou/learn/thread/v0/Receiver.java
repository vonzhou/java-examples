package com.vonzhou.learn.thread.v0;

import com.vonzhou.learn.thread.Message;

import java.util.concurrent.Executors;

/**
 * @version 2016/10/11.
 */
public class Receiver {
    private static volatile boolean inited = false;
    private static volatile boolean shutdown = false;
    private static volatile int cnt = 0;

    private MessageHandler messageHandler;

    public void start(){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                while(!shutdown){
                    init();
                    recv();
                }
            }
        });
    }

    /**
     * 模拟消息接收
     */
    public void recv(){
            Message msg = new Message("Msg" + System.currentTimeMillis());
            System.out.println(String.format("接收到消息(%d): %s", ++cnt, msg));
            messageHandler.handle(msg);
    }

    public void init(){
        if(!inited){
            messageHandler = new MessageHandler();
            inited = true;
        }
    }

    public static void main(String[] args) {
        new Receiver().start();
    }
}
