package com.wyc.redis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author yuchen.wu
 * @date 2021-01-05
 */
@Component
public class OrderListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println(message);
    }
}
