package com.mola;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.date.DateUnit;
import com.mola.tokenview.config.TimedCacheUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CacheApplication implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {

        TimedCache<String, String> timedCache =  TimedCacheUtil.createCache();;
        timedCache.schedulePrune(DateUnit.HOUR.getMillis());

    }
}
