package com.xiaopotian.horse.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaopotian.horse.common.core.constant.CommonConstants;
import com.xiaopotian.horse.common.core.constant.SecurityConstants;
import com.xiaopotian.horse.common.core.constant.enums.LoginTypeEnum;
import com.xiaopotian.horse.common.core.exception.ValidateCodeException;
import com.xiaopotian.horse.common.core.util.R;
import com.xiaopotian.horse.common.core.util.WebUtils;
import com.xiaopotian.horse.gateway.config.FilterIgnorePropertiesConfig;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * ==========================================
 * 验证码处理
 * @author : 小破天
 * @date : 2019-05-17 17:35
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
@Slf4j
@Component
@AllArgsConstructor
public class ValidateCodeGatewayFilter extends AbstractGatewayFilterFactory {
    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;
    private final FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 不是登录请求，直接向下执行
            if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath()
                    , SecurityConstants.OAUTH_TOKEN_URL, SecurityConstants.SMS_TOKEN_URL
                    , SecurityConstants.SOCIAL_TOKEN_URL)) {
                return chain.filter(exchange);
            }

            // 刷新token，直接向下执行
            String grantType = request.getQueryParams().getFirst("grant_type");
            if (StrUtil.equals(SecurityConstants.REFRESH_TOKEN, grantType)) {
                return chain.filter(exchange);
            }

            // 终端设置不校验， 直接向下执行
            try {
                String[] clientInfos = WebUtils.getClientId(request);
                if (filterIgnorePropertiesConfig.getClients().contains(clientInfos[0])) {
                    return chain.filter(exchange);
                }

                // 如果是社交登录，判断是否包含SMS
                if (StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), SecurityConstants.SOCIAL_TOKEN_URL)) {
                    String mobile = request.getQueryParams().getFirst("mobile");
                    if (StrUtil.containsAny(mobile, LoginTypeEnum.SMS.getType())) {
                        throw new ValidateCodeException("验证码不合法");
                    } else {
                        return chain.filter(exchange);
                    }
                }

                //校验验证码
                checkCode(request);
            } catch (Exception e) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.PRECONDITION_REQUIRED);
                try {
                    return response.writeWith(Mono.just(response.bufferFactory()
                            .wrap(objectMapper.writeValueAsBytes(
                                    R.builder().msg(e.getMessage())
                                            .code(CommonConstants.FAIL).build()))));
                } catch (JsonProcessingException e1) {
                    log.error("对象输出异常", e1);
                }
            }

            return chain.filter(exchange);
        };
    }

    /**
     * 检查code
     *
     * @param request
     */
    @SneakyThrows
    private void checkCode(ServerHttpRequest request) {
        String code = request.getQueryParams().getFirst("code");

        if (StrUtil.isBlank(code)) {
            throw new ValidateCodeException("验证码不能为空");
        }

        String randomStr = request.getQueryParams().getFirst("randomStr");
        if (StrUtil.isBlank(randomStr)) {
            randomStr = request.getQueryParams().getFirst("mobile");
        }

        String key = CommonConstants.DEFAULT_CODE_KEY + randomStr;
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        if (!redisTemplate.hasKey(key)) {
            throw new ValidateCodeException("验证码不合法");
        }

        Object codeObj = redisTemplate.opsForValue().get(key);

        if (codeObj == null) {
            throw new ValidateCodeException("验证码不合法");
        }

        String saveCode = codeObj.toString();
        if (StrUtil.isBlank(saveCode)) {
            redisTemplate.delete(key);
            throw new ValidateCodeException("验证码不合法");
        }

        if (!StrUtil.equals(saveCode, code)) {
            redisTemplate.delete(key);
            throw new ValidateCodeException("验证码不合法");
        }

        redisTemplate.delete(key);
    }
}

