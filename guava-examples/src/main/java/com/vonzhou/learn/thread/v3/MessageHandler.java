package com.vonzhou.learn.thread.v3;

import java.util.concurrent.*;

/**
 * 使用多线程处理消息
 *
 * @version 2016/10/11.
 */
public class MessageHandler {
    private static final int THREAD_POOL_SIZE = 4;
    private static final int BLOCK_QUEUE_CAP = 500;
    ThreadPoolExecutor service = new ThreadPoolExecutor(THREAD_POOL_SIZE, THREAD_POOL_SIZE, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(BLOCK_QUEUE_CAP), new SimpleThreadFactory(), new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("自定义拒绝策略");
            try {
                executor.getQueue().put(r);
                System.out.println("重新放任务回队列");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    public void handle(final Message msg) {
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
    public void parseMsg(Message message) {
        try {
            Thread.sleep(5000);
            System.out.println("线程名:" + Thread.currentThread().getName());
            System.out.println("解析消息：" + message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class SimpleThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("Thread-" + System.currentTimeMillis());
            return thread;
        }
    }
}
