package com.wyc.mq.demo;

import java.util.concurrent.Semaphore;

import lombok.SneakyThrows;

import com.wyc.mq.core.WmqBroker;
import com.wyc.mq.core.WmqConsumer;
import com.wyc.mq.core.WmqMessage;
import com.wyc.mq.core.WmqProducer;

/**
 * @author yuchen.wu
 * @date 2021-01-19
 */

public class WmqDemo {

    @SneakyThrows
    public static void main(String[] args) {
        String topic = "wyc.test";
        WmqBroker broker = new WmqBroker();
        broker.createTopic(topic);

        WmqConsumer consumer = broker.createConsumer();
        consumer.subscribe(topic);

        boolean[] flag = new boolean[1];
        new Thread(() -> {
            while (!flag[0]) {
                WmqMessage<Order> message = consumer.poll(100);
                if (null != message) {
                    System.out.println(message.getBody());
                }
            }
            System.out.println("程序退出");
        }).start();
        WmqProducer producer = broker.createProducer();
        for (int i = 0; i < 1000; i++) {
            Order order = new Order(1000L + i, System.currentTimeMillis(), "USD2CNY", 6.51d);
            producer.send(topic, new WmqMessage<>(null, order));
        }
        Thread.sleep(500);
        System.out.println("点击任何键，发送一条消息；点击q或e，退出程序。");
        while (true) {
            char c = (char) System.in.read();
            if(c > 20) {
                System.out.println(c);
                producer.send(topic, new WmqMessage(null, new Order(100000L + c, System.currentTimeMillis(), "USD2CNY", 6.52d)));
            }

            if( c == 'q' || c == 'e') break;
        }

        flag[0] = true;
    }

}
