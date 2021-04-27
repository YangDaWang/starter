package com.yxy.redis.starter.config;

import com.yxy.redis.core.builder.RedisBuilder;
import com.yxy.redis.core.model.RedisProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

/**
 * @author wuenci
 * @date 2020年08月14日
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
@ConditionalOnClass(value = {RedisProperties.class, Jedis.class})
public class LyRedisAutoConfiguration {

    @Bean
    @SuppressWarnings("unqualified-field-access")
    public JedisConnectionFactory jedisConnectionFactory(RedisProperties properties) {
        return RedisBuilder.buildJedisConnectionFactory(properties);
    }

    @Bean
    public StringRedisTemplate redisTemplate(JedisConnectionFactory factory) {
        return RedisBuilder.buildRedisTemplate(factory);
    }
}
