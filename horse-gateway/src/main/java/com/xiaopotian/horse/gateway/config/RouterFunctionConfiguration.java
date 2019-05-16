package com.xiaopotian.horse.gateway.config;

import com.xiaopotian.horse.gateway.handler.HystrixFallbackHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

/**
 * ==========================================
 * 路由配置信息
 *
 * @author : 小破天
 * @date : 2019-05-15 20:40
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class RouterFunctionConfiguration {
    private final HystrixFallbackHandler hystrixFallbackHandler;

    /**
     * RouterFunction 与 @Controller 类中的 @RequestMapping 注解类似
     * @return
     */
    @Bean
    public RouterFunction routerFunction(){

    }
}
