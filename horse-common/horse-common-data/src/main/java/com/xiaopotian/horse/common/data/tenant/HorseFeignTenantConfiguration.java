package com.xiaopotian.horse.common.data.tenant;

import feign.RequestInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ==========================================
 * feign 租户信息拦截
 *
 * @author : 小破天
 * @date : 2019-05-29 19:19
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
@Configuration
public class HorseFeignTenantConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor(){
        return new HorseFeignTenantInterceptor();
    }
}
