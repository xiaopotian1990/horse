package com.xiaopotian.horse.biz.order.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * ==========================================
 * Created with IntelliJ IDEA.
 * User: 小破天
 * Date: 2019-04-05
 * Time: 20:12
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
@Component
@ConfigurationProperties(prefix = "horse")
@Data
@RefreshScope
public class HorseConfigProperties {
    private String name;
    private Integer age;
}
