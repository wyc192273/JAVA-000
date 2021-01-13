package com.wyc.mq;

import java.util.concurrent.atomic.AtomicInteger;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yuchen.wu
 * @date 2021-01-11
 */
public class Application {

    public static void main(String[] args) throws InterruptedException, JMSException {
//        testDestination(new ActiveMQTopic("test.topic"));
        testDestination(new ActiveMQTopic("test.queue"));
    }

    public static void testDestination(Destination destination) throws JMSException, InterruptedException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61617");
        ActiveMQConnection conn = (ActiveMQConnection) factory.createConnection();
        conn.start();

        Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(destination);
        final AtomicInteger count = new AtomicInteger(0);
        MessageListener listener = message -> {
            try {
                System.out.println("count = "
                        + count.incrementAndGet()
                        + " ==> receive from"
                        + destination.toString()
                        + " : "
                        + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        consumer.setMessageListener(listener);
        // 创建生产者，生产100个消息
        MessageProducer producer = session.createProducer(destination);
        int index = 0;
        while (index++ < 100) {
            TextMessage message = session.createTextMessage(index + " message.");
            producer.send(message);
        }

        Thread.sleep(2000);
        session.close();
        conn.close();
    }
}
