package com.wyc.redis.controller;

import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuchen.wu
 * @date 2021-01-03
 */
@RestController
public class TestController {

    @Resource
    private RedisTemplate<String, String> stringRedisTemplate;


    @GetMapping("lock")
    public Object lock() throws InterruptedException {
        return stringRedisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
            boolean result = redisConnection.setNX("lock_key".getBytes(), "1".getBytes());
            if (result) {
                redisConnection.expire("lock_key".getBytes(), 5);
                return true;
            }
            return false;
        });
    }

}
