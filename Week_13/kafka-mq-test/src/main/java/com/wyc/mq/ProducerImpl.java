package com.wyc.mq;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.alibaba.fastjson.JSON;

/**
 * @author yuchen.wu
 * @date 2021-01-12
 */

public class ProducerImpl implements Producer {

    private KafkaProducer<String, String> producer;
    private static final String TOPIC = "test32";

    public ProducerImpl() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
        //        properties.put("queue.enqueue.timeout.ms", -1);
        //        properties.put("enable.idempotence", true);
        //        properties.put("transactional.id", "transactional_1");
        //        properties.put("acks", "all");
        //        properties.put("retries", "3");
        //        properties.put("max.in.flight.requests.per.connection", 1);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(properties);
        //producer.initTransactions();
    }

    @Override
    public void send(Object object) {
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, JSON.toJSONString(object));
        Future<RecordMetadata> future = producer.send(record, new Callback() {

            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                System.out.println(recordMetadata);
                e.printStackTrace();
            }
        });
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ProducerImpl producer = new ProducerImpl();
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setId(i);
            user.setName("user" + i);
            producer.send(user);
        }

    }

    @Override
    public void close() {
        producer.close();
    }
}
