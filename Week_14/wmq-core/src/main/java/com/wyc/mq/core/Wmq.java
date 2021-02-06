package com.wyc.mq.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

import lombok.SneakyThrows;

/**
 * @author yuchen.wu
 * @date 2021-01-19
 */

public class Wmq {

    private String topic;
    private int capacity;
    private WmqMessage[] queue;
    private int producerOffset;
    private static final Object consumerLockObject = new Object();
    private ReentrantLock producerLock = new ReentrantLock();
    private Map<String, Integer> CONSUMER_GROUP_OFFSET;

    public Wmq(String topic, int capacity) {
        this.producerOffset = 0;
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new WmqMessage[capacity];
        this.CONSUMER_GROUP_OFFSET = new ConcurrentHashMap<>();
    }

    public boolean send(WmqMessage message) {
        if (producerOffset >= capacity) {
            throw new IllegalStateException("queue is full");
        }
        producerLock.lock();
        try{
            queue[producerOffset] = message;
            producerOffset++;
            for (String group : CONSUMER_GROUP_OFFSET.keySet()) {
                synchronized (group) {
                    group.notifyAll();
                }
            }
            return true;
        }finally {
            producerLock.unlock();
        }
    }

    @SneakyThrows
    public WmqMessage poll(final String consumerGroup) {
        synchronized (consumerGroup) {
            int offset = CONSUMER_GROUP_OFFSET.computeIfAbsent(consumerGroup, s -> 0);
            while (offset == producerOffset) {
                consumerGroup.wait();
            }
            
        }
    }

    @SneakyThrows
    public WmqMessage poll(String consumerGorup, long timeount) {
        return queue.poll(timeount, TimeUnit.MICROSECONDS);
    }
}
