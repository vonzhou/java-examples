package com.vonzhou.learn.message;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.*;
import java.util.concurrent.Executors;

/**
 * Kafka Broker 配置参数含义 'https://kafka.apache.org/08/configuration.html'
 * 参考 'https://cwiki.apache.org/confluence/display/KAFKA/Consumer+Group+Example'
 *
 * @version 2016/8/25.
 */
public class MessageConsumer {
    private ConsumerConnector consumer;
    private String topic;

    public static void main(String[] arg) {
        new MessageConsumer().start();
    }

    public void init(){
        // 指定 zookeeper 的地址
//        String zookeeper = "localhost:2181";
        String zookeeper = "10.165.124.132:2181";
        String topic = "test";
        String groupId = "test-group";

        Properties props = new Properties();
        /**
         * 必须的配置
         */
        props.put("zookeeper.connect", zookeeper);
        /**
         * 必须的配置， 代表该消费者所属的 consumer group
         */
        props.put("group.id", groupId);
        /**
         * 多长时间没有发送心跳信息到zookeeper就会认为其挂掉了，默认是6000
         */
        props.put("zookeeper.session.timeout.ms", "6000");
        /**
         * 可以允许zookeeper follower 比 leader慢的时长
         */
        props.put("zookeeper.sync.time.ms", "200");
        /**
         * 控制consumer offsets提交到zookeeper的频率， 默认是60 * 1000
         */
        props.put("auto.commit.interval.ms", "1000");


        consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(props));
        this.topic = topic;
    }
    public void consume() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, 1);
        /**
         * createMessageStreams 为每个topic创建 message stream
         */
        Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = messageStreams.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
        while (iterator.hasNext()) {
            try {
                String message = new String(iterator.next().message());
                System.out.println("收到消息" + message);
            } catch (Throwable e) {
                System.out.println(e.getCause());
            }
        }

    }

    public void start() {
        System.out.println("开始消费消息...");
        Executors.newSingleThreadExecutor().execute(new Runnable() {

            public void run() {
                init();
                while (true) {
                    try {
                        consume();
                    } catch (Throwable e) {
                        if (consumer != null) {
                            try {
                                consumer.shutdown();
                            } catch (Throwable e1) {
                                System.out.println("Turn off Kafka consumer error! " + e);
                            }
                        }
                    }
                }
            }
        });
    }
}
