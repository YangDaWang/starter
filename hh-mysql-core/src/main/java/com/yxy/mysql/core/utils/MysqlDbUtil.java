package com.yxy.mysql.core.utils;

import com.yxy.mysql.core.model.MysqlDbProperties;
import java.util.Map.Entry;
import org.springframework.util.CollectionUtils;


public class MysqlDbUtil {

    /**
     * 生成mysql db url`
     *
     * @param dbConfig mysql配置项
     * @return mysql连接url
     */
    public static String generateDbUrl(MysqlDbProperties dbConfig) {
        StringBuilder url = new StringBuilder("jdbc:mysql://")
                .append(dbConfig.getHost()).append(":")
                .append(dbConfig.getPort()).append("/")
                .append(dbConfig.getDbname());
        boolean first = true;
        if (!CollectionUtils.isEmpty(dbConfig.getExtraParam())) {
            for (Entry<String, String> entry : dbConfig.getExtraParam().entrySet()) {
                url.append(first ? "?" : "&").append(entry.getKey()).append("=")
                        .append(entry.getValue());
                first = false;
            }
        }
        return url.toString();
    }
}
