package com.wyc.mq;

import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yuchen.wu
 * @date 2021-01-11
 */
@SpringBootApplication
public class ActiveMqServer {

    public static void main(String[] args) throws Exception {
        BrokerService broker = new BrokerService();
        broker.setUseJmx(true);
        broker.setBrokerName("localBroker");
        broker.setPersistent(false);
        broker.addConnector("tcp://127.0.0.1:61617");
        broker.start();
        SpringApplication.run(ActiveMqServer.class, args);

    }

}
