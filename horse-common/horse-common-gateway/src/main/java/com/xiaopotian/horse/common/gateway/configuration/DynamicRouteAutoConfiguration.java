package com.xiaopotian.horse.common.gateway.configuration;

import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.config.PropertiesRouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 动态路由配置类
 *
 * @author zouLu
 * @date 2019-04-17 17:11
 **/
@Configuration
@ComponentScan("com.xiaopotian.horse.common.gateway")
public class DynamicRouteAutoConfiguration {
    /**
     * 配置文件设置为空
     * redis 加载为准
     * 初始化配置路由定义加载器 从配置文件(GatewayProperties 例如，YML / Properties 等 ) 读取RouteDefinition
     *
     * @return
     */
    @Bean
    public PropertiesRouteDefinitionLocator propertiesRouteDefinitionLocator() {
        return new PropertiesRouteDefinitionLocator(new GatewayProperties());
    }
}
