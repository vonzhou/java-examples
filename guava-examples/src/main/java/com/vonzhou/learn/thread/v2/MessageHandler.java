package com.vonzhou.learn.thread.v2;

import java.util.concurrent.*;

/**
 * 使用多线程处理消息
 * @version 2016/10/11.
 */
public class MessageHandler {
    private static final int THREAD_POOL_SIZE = 1;
    private ExecutorService service = Executors.newFixedThreadPool(THREAD_POOL_SIZE);


    public void handle(final Message msg){
        try {
            service.execute(new Runnable() {

                @Override
                public void run() {
                    parseMsg(msg);
                }
            });
        } catch (Throwable e) {
            System.out.println("消息处理异常" + e);
        }
    }

    /**
     * 比较耗时的消息处理流程
     */
    public void parseMsg(Message message){
        try {
            Thread.sleep(10000);
            System.out.println("解析消息：" + message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            Receiver.limit --;
        }

    }
}
