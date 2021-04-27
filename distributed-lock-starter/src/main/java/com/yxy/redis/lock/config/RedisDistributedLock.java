package com.yxy.redis.lock.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author xinyuyang
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisDistributedLock {

    /**
     * 获取锁超时时间,单位秒
     */
    long waitTime() default 2;

    /**
     * 释放锁超时时间,单位秒
     */
    long leaseTime() default 500;

    /**
     *
     */
    TimeUnit timeunit() default TimeUnit.SECONDS;

}