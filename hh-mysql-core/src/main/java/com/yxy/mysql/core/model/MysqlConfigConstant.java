package com.yxy.mysql.core.model;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

import org.springframework.boot.jdbc.DatabaseDriver;

/**
 * @author wuenci
 * @date 2020年08月14日
 */
public class MysqlConfigConstant {

    /**
     * 默认连接池配置
     */
    public static final long DEFAULT_CONNECTION_TIMEOUT = SECONDS.toMillis(30);
    public static final long DEFAULT_IDLE_TIMEOUT = MINUTES.toMillis(10);
    public static final long DEFAULT_MAX_LIFETIME = MINUTES.toMillis(30);
    public static final int DEFAULT_MAX_POOL_SIZE = 15;
    public static final int DEFAULT_MIN_IDLE = 5;
    public static final int DEFAULT_PORT = 3306;
    public static final String DEFAULT_CONNECTION_TEST_QUERY = "SELECT 1";
    public static final String DEFAULT_DRIVE_CLASS_NAME = DatabaseDriver.MYSQL.getDriverClassName();
    public static final String DEFAULT_MAPPER_LOCATION = "classpath*:/mapper/*.xml";
}
