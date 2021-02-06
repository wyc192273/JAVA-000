package com.wyc.mq.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yuchen.wu
 * @date 2021-01-19
 */

public class WmqBroker {

    public static final int CAPACITY = 10000;
    private final Map<String, Wmq> wmqMap = new ConcurrentHashMap<>();

    public void createTopic(String topic) {
        wmqMap.putIfAbsent(topic, new Wmq(topic, CAPACITY));
    }

    public Wmq findWmq(String topic) {
        return wmqMap.get(topic);
    }

    public WmqProducer createProducer() {
        return new WmqProducer(this);
    }

    public WmqConsumer createConsumer() {
        return new WmqConsumer(this);
    }
}
