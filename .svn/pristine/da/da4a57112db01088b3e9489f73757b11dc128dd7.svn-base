package com.jxlt.udic.riskcontrol.website.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * cache config
 *
 * @author
 * @create 2018-12-28 19:30
 **/
//@Configuration
public class CacheConfig {

    /**
     * 自定义cacheManager（无论是否使用自定义的名称）后，原来默认的cacheManager就失效了
     *
     * @date 2018/12/28 20:37
     * @author
     */
    @Bean("caffeineCacheManager")
    public CacheManager cacheManager() {

        SimpleCacheManager cacheManager = new SimpleCacheManager();

        //自定义两个CaffeineCache，可分别设置不同的参数
        //仅测试用，正式用时，要把所有常量抽到配置文件中自定义配置
        //一个Caffeine只能build一个cache，所以需要分别Caffeine.newBuilder()
        CaffeineCache cache1 = new CaffeineCache("user1", Caffeine.newBuilder()
                .expireAfterWrite(20L, TimeUnit.SECONDS)
                .maximumSize(50)
                .build());
        CaffeineCache cache2 = new CaffeineCache("user2", Caffeine.newBuilder()
                .expireAfterWrite(30L, TimeUnit.SECONDS)
                .maximumSize(60)
                .build());

        //注入到管理器中
        cacheManager.setCaches(Arrays.asList(cache1, cache2));
        return cacheManager;
    }
}

