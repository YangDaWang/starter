package com.yxy.redis.lock.config;

import javax.annotation.Resource;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;

/**
 * @Author XinYu Yang
 * @date 2020/8/28  10:40 上午
 */
@ConditionalOnClass(RedisProperties.class)
public class RedissonConfig {
    @Resource
    RedisProperties redisProperties;
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(String.format("redis://%s:%s", redisProperties.getHost(),
                        redisProperties.getPort())).setDatabase(redisProperties.getDatabase())
                .setDatabase(redisProperties.getDatabase()).setPassword(redisProperties.getPassword());
        return Redisson.create(config);
    }
}
