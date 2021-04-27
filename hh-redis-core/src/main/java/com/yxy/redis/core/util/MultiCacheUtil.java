package com.yxy.redis.core.util;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wuenci
 * @date 2020年10月20日
 */
public abstract class MultiCacheUtil<K, V> {

    public Map<K, V> getAll(List<K> keyList) {
        Map<K, V> valueFromCache = getAllFromCache(keyList);

        if (valueFromCache.size() == keyList.size()) {
            return valueFromCache;
        }
        Map<K, V> resultMap = Maps.newHashMapWithExpectedSize(keyList.size());
        resultMap.putAll(valueFromCache);
        Set<K> keyFromCache = valueFromCache.keySet();

        List<K> keyToDb = keyList.stream().filter(o -> !keyFromCache.contains(o))
                .collect(Collectors.toList());
        Map<K, V> valueFromDb = getAllFromDb(keyToDb);
        if (valueFromDb != null && valueFromDb.size() > 0) {
            resultMap.putAll(valueFromDb);
            saveToCache(valueFromDb);
        }

        return resultMap;
    }


    protected abstract Map<K, V> getAllFromCache(List<K> keyList);

    protected abstract void saveToCache(Map<K, V> map);

    protected abstract Map<K, V> getAllFromDb(List<K> keyList);
}
