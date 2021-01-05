package com.wyc.redisson.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;

import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
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
    private RedissonClient redissonClient;
    @Resource
    private RedisTemplate<String, String> stringRedisTemplate;


    @GetMapping("lock")
    public Object lock() throws InterruptedException {
        RLock rLock = redissonClient.getLock("lock_key");
        rLock.lock(5, TimeUnit.SECONDS);
        return true;
    }

    @GetMapping("try_lock")
    public Object tryLock() throws InterruptedException {
        RLock rLock = redissonClient.getLock("lock_key");
        return rLock.tryLock(10, TimeUnit.SECONDS);
    }

    @GetMapping("release_lock")
    public Object releaseLock() throws InterruptedException {
        RLock rLock = redissonClient.getLock("lock_key");
        return rLock.forceUnlock();
    }

    @GetMapping("init_inventory")
    public Object initInventory() {
        redissonClient.getBucket("inventory").set(1000);
        return true;
    }

    @GetMapping("cost_inventory")
    public Object costInventory(int count) throws InterruptedException {
        lock();
        Integer total = Integer.parseInt((String) redissonClient.getBucket("inventory").get());
        if (total >= count) {
            redissonClient.getBucket("inventory").set(total - count);
        }
        releaseLock();
        return true;
    }

}
