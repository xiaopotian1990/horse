package com.xiaopotian.horse.common.data.cache;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * ==========================================
 * CacheManagerCustomizers配置
 * User: 小破天
 * Date: 2019-05-30
 * Time: 0:53
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
@Configuration
@ConditionalOnMissingBean(CacheManagerCustomizers.class)
public class RedisCacheManagerConfig {
    @Bean
    public CacheManagerCustomizers cacheManagerCustomizers(
            ObjectProvider<List<CacheManagerCustomizer<?>>> customizers) {
        return new CacheManagerCustomizers(customizers.getIfAvailable());
    }
}
