package com.yxy.mysql.core.model;

import static com.yxy.mysql.core.model.MysqlConfigConstant.DEFAULT_CONNECTION_TEST_QUERY;
import static com.yxy.mysql.core.model.MysqlConfigConstant.DEFAULT_CONNECTION_TIMEOUT;
import static com.yxy.mysql.core.model.MysqlConfigConstant.DEFAULT_DRIVE_CLASS_NAME;
import static com.yxy.mysql.core.model.MysqlConfigConstant.DEFAULT_IDLE_TIMEOUT;
import static com.yxy.mysql.core.model.MysqlConfigConstant.DEFAULT_MAX_LIFETIME;
import static com.yxy.mysql.core.model.MysqlConfigConstant.DEFAULT_MAX_POOL_SIZE;
import static com.yxy.mysql.core.model.MysqlConfigConstant.DEFAULT_MIN_IDLE;
import static com.yxy.mysql.core.model.MysqlConfigConstant.DEFAULT_PORT;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="mailto:dongyuxiang@vpgame.cn">dongyuxiang</a>
 * @date 2020/07/23 10:07
 **/
@Data
@ConfigurationProperties("hh.datasource")
public class MysqlDbProperties {

    private static final long serialVersionUID = 3252815319892883794L;

    /**
     * 数据库用户名
     */
    private String username;

    /**
     * 数据库用户密码
     */
    private String password;

    /**
     * 数据库库名
     */
    private String dbname;

    /**
     * 参数配置ip地址
     */
    private String host;

    /**
     * 参数配置端口
     */
    private int port = DEFAULT_PORT;

    /**
     * 数据库驱动类型 默认com.mysql.cj.jdbc.Driver
     */
    private String driverClassName = DEFAULT_DRIVE_CLASS_NAME;

    /**
     * 连接池配置
     */
    private boolean autoCommit = true;

    /**
     * 探活sql
     */
    private String connectionTestQuery = DEFAULT_CONNECTION_TEST_QUERY;

    /**
     * 连接超时, 默认30,000ms
     */
    private Long connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;

    /**
     * 空闲超时时间, 默认10分钟
     */
    private Long idleTimeout = DEFAULT_IDLE_TIMEOUT;

    /**
     * 最大生命周期, 默认30分钟
     */
    private Long maxLifetime = DEFAULT_MAX_LIFETIME;

    /**
     * 最大连接数, 默认15
     */
    private Integer maximumPoolSize = DEFAULT_MAX_POOL_SIZE;

    /**
     * 最小空闲连接数, 默认15
     */
    private Integer minimumIdle = DEFAULT_MIN_IDLE;

    /**
     * 初始sql, 默认SELECT 1
     */
    private String connectionInitSql = DEFAULT_CONNECTION_TEST_QUERY;

    /**
     * 额外参数
     */
    private Map<String, String> extraParam = Maps.newHashMap(DEFAULT_PARAM);


    /**
     * 默认额外参数
     */
    private static final Map<String, String> DEFAULT_PARAM = ImmutableMap
            .of("useUnicode", "true", "characterEncoding", "utf-8", "serverTimezone",
                    "Asia/Shanghai", "zeroDateTimeBehavior", "convertToNull");

    public void putParam(String key, String value) {
        extraParam.put(key, value);
    }


    public String getConnectionTestQuery() {
        return connectionTestQuery;
    }
}
