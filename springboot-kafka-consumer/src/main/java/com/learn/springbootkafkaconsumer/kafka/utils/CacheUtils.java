package com.learn.springbootkafkaconsumer.kafka.utils;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2020-8-28 20:13:13
 */
public class CacheUtils {
    private static Object cacheData;

    public static Object getCacheData() {
        return cacheData;
    }

    public static void setCacheData(Object cacheData) {
        CacheUtils.cacheData = cacheData;
    }
}
