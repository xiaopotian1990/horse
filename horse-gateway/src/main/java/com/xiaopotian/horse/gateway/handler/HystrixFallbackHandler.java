package com.xiaopotian.horse.gateway.handler;

import com.xiaopotian.horse.common.core.constant.CommonConstants;
import com.xiaopotian.horse.common.core.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;

/**
 * ==========================================
 * Hystrix 降级处理
 *
 * @author : 小破天
 * @date : 2019-05-15 20:43
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
@Slf4j
@Component
public class HystrixFallbackHandler implements HandlerFunction<ServerResponse> {
    /**
     * 网关在转发请求之前，会把一些关键属性放到请求体的attribute中，通过GATEWAY_ORIGINAL_REQUEST_URL_ATTR可以获取原请求 URL。
     * 如果通过RequestContextHolder的方式获取，那么获取到的其实是服务降级后 forward 请求，这里为/fallback
     *
     * @param serverRequest
     * @return
     */
    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        Optional<Object> url = serverRequest.attribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        url.ifPresent(u -> log.error("网关执行请求:{}失败,hystrix服务降级处理", url));

        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(R.builder()
                        .msg("服务异常")
                        .code(CommonConstants.FAIL)
                        .build()));
    }
}
