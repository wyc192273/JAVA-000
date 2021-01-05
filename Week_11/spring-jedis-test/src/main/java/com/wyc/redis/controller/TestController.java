package com.wyc.redis.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;

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
//                redisConnection.expire("lock_key".getBytes(), 5);
                return true;
            }
            return false;
        });
    }

    @GetMapping("init_inventory")
    public Object initInventory() {
        stringRedisTemplate.opsForValue().set("inventory", "500");
        return true;
    }

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    @GetMapping("cost_inventory")
    public Object costInventory(int count) throws InterruptedException {
        boolean lock = (boolean) lock();
        if (!lock) {
            return false;
        }
        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();
        // 异步线程 用来做 异步续期
        executorService.execute(() -> {
            while (true) {
                try {
                    boolean flag = semaphore.tryAcquire(3, TimeUnit.SECONDS);
                    if (!flag) {
                        renewalLock();
                    } else {
                        break;
                    }
                } catch (InterruptedException e) {

                }
            }
        });
        try {
            String totalStr = stringRedisTemplate.opsForValue().get("inventory");
            if (totalStr == null) {
                return false;
            }
            int total = Integer.parseInt(totalStr);
            if (total >= count) {
                stringRedisTemplate.opsForValue().increment("inventory", -count);
            }
            return true;
        } finally {
            semaphore.release();
            releaseLock();
        }
    }

    @GetMapping("pub")
    public Object pub(String message) {
        stringRedisTemplate.convertAndSend("order_channel", message);
        return true;
    }

    private void releaseLock() {
        stringRedisTemplate.delete("lock_key");
    }

    private void renewalLock() {
        stringRedisTemplate.expire("lock_key", 5, TimeUnit.SECONDS);
    }

}
