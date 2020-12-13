package com.mola;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.date.DateUnit;

public class CacheTest {

    public static void main(String[] args) {
        TimedCache<String, String> timedCache = CacheUtil.newTimedCache(DateUnit.DAY.getMillis());

        timedCache.put("key1", "value1");//1毫秒过期

        timedCache.schedulePrune(DateUnit.HOUR.getMillis());



    }
}
