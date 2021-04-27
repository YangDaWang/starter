package com.yxy.mysql.starter.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.yxy.mysql.core.builder.MySqlDataSourceBuilder;
import com.yxy.mysql.core.model.MysqlDbProperties;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MysqlDbProperties.class)
@ConditionalOnClass(value = {MysqlDbProperties.class, HikariDataSource.class})
public class LyDatasourceAutoConfiguration {

    @Bean
    @SuppressWarnings("unqualified-field-access")
    public HikariDataSource dataSource(MysqlDbProperties properties) {
        return MySqlDataSourceBuilder.buildDataSource(properties);
    }

    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(HikariDataSource dataSource)
            throws IOException {
        return MySqlDataSourceBuilder.buildSqlSessionFactoryBean(dataSource);
    }
}
