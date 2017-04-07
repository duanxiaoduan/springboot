package com.xx.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * Created by jishu04 on 2016/9/1.
 */
//@Configuration
public class GuavaCacheConfig {

    @Bean(name = "guavaCacheManager")
    public GuavaCacheManager guavaCacheManager() {
        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.DAYS);
        GuavaCacheManager cacheManager = new GuavaCacheManager("cache");
        cacheManager.setCacheBuilder(cacheBuilder);
        return cacheManager;
    }
}
