package com.wyc.redis;

import java.io.Serializable;
import java.time.Duration;
import javax.annotation.Resource;

import redis.clients.jedis.JedisPoolConfig;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.wyc.redis.listener.OrderListener;

/**
 * @author yuchen.wu
 * @date 2021-01-03
 */
@Configuration
public class JedisRedisConfig {

    @Resource
    private RedisProperties redisProperties;


    /**
     * 连接池配置信息
     */

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大连接数
        jedisPoolConfig.setMaxTotal(redisProperties.getJedis().getPool().getMaxActive());
        // 当池内没有可用连接时，最大等待时间
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getJedis().getPool().getMaxWait().toMillis());
        // 最大空闲连接数
        jedisPoolConfig.setMinIdle(redisProperties.getJedis().getPool().getMinIdle());
        // 最小空闲连接数
        jedisPoolConfig.setMinIdle(redisProperties.getJedis().getPool().getMaxIdle());
        // 其他属性可以自行添加
        return jedisPoolConfig;
    }

    /**
     * Jedis 连接
     *
     * @param jedisPoolConfig
     * @return
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling()
                .poolConfig(jedisPoolConfig).and().readTimeout(redisProperties.getTimeout()).build();
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisProperties.getPassword()));
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }

    /**
     * 缓存管理器
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        return RedisCacheManager.create(connectionFactory);
    }

    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(JedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(jedisConnectionFactory(jedisPoolConfig()));
        return redisTemplate;
    }

    /**
     * 消息监听器，使用MessageAdapter可实现自动化解码及方法代理
     *
     * @return
     */
    @Bean
    public MessageListenerAdapter listener(OrderListener subscriber) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(subscriber, "onMessage");
        adapter.setSerializer(new GenericJackson2JsonRedisSerializer());
        adapter.afterPropertiesSet();
        return adapter;
    }

    /**
     * 将订阅器绑定到容器
     *
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listener) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listener, new PatternTopic("order_channel"));
        return container;
    }
}
