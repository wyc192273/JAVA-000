package com.wyc.redisson;

import java.nio.charset.Charset;
import javax.annotation.Resource;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author yuchen.wu
 * @date 2021-01-03
 */
@Configuration
public class RedisConfig {

    @Resource
    private RedisProperties redisProperties;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + redisProperties.getHost() + ":" + redisProperties.getPort())
                .setPassword(redisProperties.getPassword());
        config.setCodec(new StringCodec(Charset.forName("utf-8")));
        return Redisson.create(config);
    }

    @Bean
    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redisson) {
        return new RedissonConnectionFactory(redisson);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate template = new RedisTemplate();

        template.setConnectionFactory(factory);

        RedisSerializer keySerializer = new StringRedisSerializer();

        template.setKeySerializer(keySerializer);
        template.setHashKeySerializer(keySerializer);

        GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer();

        // 设置内容序列化类
        template.setValueSerializer(jsonSerializer);

        template.afterPropertiesSet();

        return template;
    }
}
