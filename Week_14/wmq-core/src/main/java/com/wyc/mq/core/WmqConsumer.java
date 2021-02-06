package com.wyc.mq.core;

/**
 * @author yuchen.wu
 * @date 2021-01-19
 */

public class WmqConsumer {

    private WmqBroker broker;
    private Wmq wmq;
    private String consumerGroup;

    public WmqConsumer(WmqBroker broker) {
        this.broker = broker;
    }

    public void subscribe(String topic, String consumerGroup) {
        Wmq wmq = broker.findWmq(topic);
        if (null == wmq) {
            throw new RuntimeException("Topic[" + topic + "] does't exist");
        }
        this.wmq = wmq;
        this.consumerGroup = consumerGroup;
    }

    public WmqMessage poll() {
        return wmq.poll(consumerGroup);
    }

    public WmqMessage poll(long timeout) {
        return wmq.poll(timeout);
    }
}
