package com.xiaopotian.horse.gateway.filter;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

/**
 * ==========================================
 *演示环境过滤处理
 * @author : 小破天
 * @date : 2019-05-17 17:35
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
@Slf4j
@Component
public class PreviewGatewayFilter extends AbstractGatewayFilterFactory {
    private static final String TOKEN = "token";


    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // GET，直接向下执行
            if (StrUtil.equalsIgnoreCase(request.getMethodValue(), HttpMethod.GET.name()) ||
                    StrUtil.containsIgnoreCase(request.getURI().getPath(), TOKEN)) {
                return chain.filter(exchange);
            }

            log.warn("演示环境不能操作-> {},{}", request.getMethodValue(), request.getURI().getPath());
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.LOCKED);
            return response.setComplete();
        };
    }
}

