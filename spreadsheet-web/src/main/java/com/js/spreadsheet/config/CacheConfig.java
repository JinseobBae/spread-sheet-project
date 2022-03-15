package com.js.spreadsheet.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager(){
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        ArrayList<ConcurrentMapCache> caches = new ArrayList<>();
        caches.add(new ConcurrentMapCache("sheet"));
        caches.add(new ConcurrentMapCache("search"));

        simpleCacheManager.setCaches(caches);

        return simpleCacheManager;
    }
}
