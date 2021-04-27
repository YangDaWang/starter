package com.yxy.mysql.core.builder;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.yxy.mysql.core.model.MysqlConfigConstant;
import com.yxy.mysql.core.model.MysqlDbProperties;
import com.yxy.mysql.core.utils.MysqlDbUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import javax.sql.DataSource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@SuppressWarnings("unused")
public class MySqlDataSourceBuilder {


    public static HikariDataSource buildDataSource(MysqlDbProperties config) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(MysqlDbUtil.generateDbUrl(config));
        hikariConfig.setUsername(config.getUsername());
        hikariConfig.setPassword(config.getPassword());
        hikariConfig.setDriverClassName(config.getDriverClassName());
        hikariConfig.setAutoCommit(config.isAutoCommit());
        hikariConfig.setConnectionTestQuery(config.getConnectionTestQuery());
        hikariConfig.setConnectionTimeout(config.getConnectionTimeout());
        hikariConfig.setIdleTimeout(config.getIdleTimeout());
        hikariConfig.setMaxLifetime(config.getMaxLifetime());
        hikariConfig.setMinimumIdle(config.getMinimumIdle());
        hikariConfig.setMaximumPoolSize(config.getMaximumPoolSize());
        hikariConfig.setConnectionInitSql(config.getConnectionInitSql());
        return new HikariDataSource(hikariConfig);
    }

    public static MybatisSqlSessionFactoryBean buildSqlSessionFactoryBean(DataSource dataSource)
            throws IOException {
        return buildSqlSessionFactoryBean(dataSource, MysqlConfigConstant.DEFAULT_MAPPER_LOCATION);
    }

    public static MybatisSqlSessionFactoryBean buildSqlSessionFactoryBean(DataSource dataSource,
            String mapperLocation) throws IOException {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setPlugins(new PaginationInterceptor[]{new PaginationInterceptor()});
        sqlSessionFactoryBean.setGlobalConfig(new GlobalConfig());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mapperLocation));
        return sqlSessionFactoryBean;
    }
}
