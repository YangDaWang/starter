package com.yxy.redis.lock.lock;

import com.yxy.redis.lock.config.RedisDistributedLock;
import java.lang.reflect.Method;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author XinYu Yang
 * @date 2020/8/21  3:10 下午
 */
@Slf4j
@Aspect
@Configuration
@ConditionalOnBean(RedissonClient.class)
public class DistributedLockAop {

    @Resource
    RedissonClient redissonClient;

    @Pointcut("@annotation(com.yxy.redis.lock.config.RedisDistributedLock)")
    private void redisLockPoint() {
    }

    /**
     * 切面处理上锁放锁
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("redisLockPoint()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        RedisDistributedLock redisLock = method.getAnnotation(RedisDistributedLock.class);
        RLock lock = redissonClient.getLock(pjp.getArgs()[0].toString());
        boolean success = lock
                .tryLock(redisLock.waitTime(), redisLock.leaseTime(), redisLock.timeunit());
        if (!success) {
            log.debug("get lock failed");
            return null;
        }
        try {
            return pjp.proceed();
        } finally {
            lock.unlock();
            log.debug("release lock : success");
        }
    }
}
