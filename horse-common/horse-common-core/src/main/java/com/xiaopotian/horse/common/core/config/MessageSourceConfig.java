package com.xiaopotian.horse.common.core.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * ==========================================
 * 国际化配置
 * User: 小破天
 * Date: 2019-04-11
 * Time: 13:47
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
@Configuration
public class MessageSourceConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        return messageSource;
    }
}
