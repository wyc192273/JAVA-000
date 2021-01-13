package com.wyc.mq;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * @author yuchen.wu
 * @date 2021-01-12
 */

public class ConsumerImpl implements Consumer {

    private KafkaConsumer<String, String> consumer;
    private static final String TOPIC = "test32";

    public ConsumerImpl() {
        Properties properties = new Properties();
        //        properties.put("enable.auto.commit", false);
        //        properties.put("isolation.level", "read_committed");
        //        properties.put("auto.offset.reset", "latest");
        properties.put("group.id", "wyc4");
        properties.put("auto.offset.reset", "earliest");
        properties.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer(properties);
    }

    @Override
    public void consume() {
        consumer.subscribe(Collections.singleton(TOPIC));
        try{
            while (true) {
                ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord o : poll) {
                    ConsumerRecord<String, String> record = o;
                    System.out.println("consume object = " + record.value());
                }
            }
        }finally {
            consumer.commitSync();
        }
    }

    public static void main(String[] args) {
        ConsumerImpl consumer = new ConsumerImpl();
        consumer.consume();
    }

    @Override
    public void close() {

    }
}
