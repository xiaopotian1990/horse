package com.xiaopotian.horse.gateway.config;

import com.xiaopotian.horse.gateway.handler.HystrixFallbackHandler;
import com.xiaopotian.horse.gateway.handler.ImageCodeHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

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
    private final ImageCodeHandler imageCodeHandler;

    /**
     * RouterFunction 与 @Controller 类中的 @RequestMapping 注解类似
     *
     * @return
     */
    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions
                .route(RequestPredicates.path("/fallback").and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), hystrixFallbackHandler)
                .andRoute(RequestPredicates.GET("/code").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), imageCodeHandler);
    }
}
