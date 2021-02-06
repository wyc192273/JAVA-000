package com.wyc.mq.core;

/**
 * @author yuchen.wu
 * @date 2021-01-19
 */

public class WmqProducer {

    private WmqBroker broker;

    public WmqProducer(WmqBroker broker) {
        this.broker = broker;
    }

    public boolean send(String topic, WmqMessage message) {
        Wmq wmq = broker.findWmq(topic);
        if (null == wmq) {
            throw new RuntimeException("Topic[" + topic + "] does't exist");
        }
        return wmq.send(message);
    }

}
