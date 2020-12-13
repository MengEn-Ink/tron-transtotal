package com.mola.tokenview.config;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.date.DateUnit;

public class TimedCacheUtil {

    private static TimedCache<String, String> createCache;

    public static TimedCache<String, String> createCache() {
        createCache=CacheUtil.newTimedCache(DateUnit.DAY.getMillis());
        return createCache;
    }


    public static boolean getCache(String key) {
        return  createCache.containsKey(key);
    }

    public static void setCache(String key,String value) {
          createCache.put(key,value);
    }

}
