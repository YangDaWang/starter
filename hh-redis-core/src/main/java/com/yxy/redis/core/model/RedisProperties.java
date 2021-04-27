package com.yxy.redis.core.model;

import static com.yxy.redis.core.model.RedisConfigConstant.DEFAULT_MAX_IDLE;
import static com.yxy.redis.core.model.RedisConfigConstant.DEFAULT_MAX_TOTAL;
import static com.yxy.redis.core.model.RedisConfigConstant.DEFAULT_MAX_WAIT_MILLIS;
import static com.yxy.redis.core.model.RedisConfigConstant.DEFAULT_MIN_IDLE;
import static com.yxy.redis.core.model.RedisConfigConstant.DEFAULT_PORT;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="mailto:dongyuxiang@vpgame.cn">dongyuxiang</a>
 * @date 2020/07/23 10:07
 **/
@Data
@ConfigurationProperties("hh.redis")
public class RedisProperties {

    /**
     * 参数配置ip地址
     */
    private String host;

    /**
     * 参数配置端口
     */
    private int port = DEFAULT_PORT;

    /**
     * 是否集群模式
     */
    private boolean cluster = false;

    /**
     * redis片区名
     */
    private Integer database;

    /**
     * 数据库用户密码
     */
    private String password;


    /**
     * 最小连接数, 默认10
     */
    private Integer minIdle = DEFAULT_MIN_IDLE;

    /**
     * 最大空闲连接数, 默认30
     */
    private Integer maxIdle = DEFAULT_MAX_IDLE;

    /**
     * 最大连接数, 默认50
     */
    private Integer maxTotal = DEFAULT_MAX_TOTAL;

    /**
     * 最大等待时间, 默认500ms
     */
    private long maxWaitMillis = DEFAULT_MAX_WAIT_MILLIS;

    /**
     * 获取连接后是否测试可用性
     */
    private boolean testOnBorrow = true;

}
