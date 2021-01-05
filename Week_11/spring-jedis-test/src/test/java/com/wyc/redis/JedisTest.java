package com.wyc.redis;

import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yuchen.wu
 * @date 2021-01-03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JedisTest {

    @Resource
    private RedisTemplate<String, String> stringRedisTemplate;


    @SuppressWarnings("ConstantConditions")
    @Test
    public void testString() throws InterruptedException {
        stringRedisTemplate.opsForValue().set("id",  "test", 1, TimeUnit.SECONDS);
        Assert.assertEquals("test", stringRedisTemplate.opsForValue().get("id"));
        Thread.sleep(1000);
        Assert.assertFalse(stringRedisTemplate.hasKey("id"));
        stringRedisTemplate.opsForValue().set("age", "1");
        Assert.assertEquals(new Long(2), stringRedisTemplate.opsForValue().increment("age", 1));
        Assert.assertEquals("2", stringRedisTemplate.opsForValue().get("age"));
        Assert.assertTrue(stringRedisTemplate.delete("age"));
    }


}