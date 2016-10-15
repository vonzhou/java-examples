package com.vonzhou.learn.thread.v2;

import java.util.concurrent.Executors;

/**
 * @version 2016/10/11.
 */
public class Receiver {
    private static volatile boolean inited = false;
    private static volatile boolean shutdown = false;
    public static volatile int limit = 0;
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
        if(limit < 100){
            Message msg = new Message("Msg" + System.currentTimeMillis());
            System.out.println(String.format("接收到消息(%d): %s", ++cnt, msg));
            messageHandler.handle(msg);
            limit ++;
        }
    }

    public void init(){
        if(!inited){
            messageHandler = new MessageHandler();
        }

    }

    public static void main(String[] args) {
        new Receiver().start();
    }
}
