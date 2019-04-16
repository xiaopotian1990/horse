package com.xiaopotian.horse.common.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * ==========================================
 * Created with IntelliJ IDEA.
 * User: 小破天
 * Date: 2019-04-11
 * Time: 13:48
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
