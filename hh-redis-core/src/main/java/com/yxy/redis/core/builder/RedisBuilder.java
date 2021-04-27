package com.yxy.redis.core.builder;

import com.yxy.redis.core.model.RedisProperties;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

@SuppressWarnings("unused")
public class RedisBuilder {

    private static JedisPoolConfig buildPoolConfig(RedisProperties config) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMinIdle(config.getMinIdle());
        poolConfig.setMaxIdle(config.getMaxIdle());
        poolConfig.setMaxTotal(config.getMaxTotal());
        poolConfig.setMaxWaitMillis(config.getMaxWaitMillis());
        poolConfig.setTestOnBorrow(config.isTestOnBorrow());
        return poolConfig;
    }

    public static JedisConnectionFactory buildJedisConnectionFactory(RedisProperties config) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
                config.getHost(), config.getPort());
        redisStandaloneConfiguration.setDatabase(config.getDatabase());
        redisStandaloneConfiguration.setPassword(config.getPassword());
        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder()
                .usePooling()
                .poolConfig(buildPoolConfig(config)).build();
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }

    public static StringRedisTemplate buildRedisTemplate(JedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(factory);
        return template;
    }

}
