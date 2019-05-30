package com.xiaopotian.horse.gateway.config;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.xiaopotian.horse.common.core.constant.CommonConstants;
import com.xiaopotian.horse.common.gateway.support.DynamicRouteInitEvent;
import com.xiaopotian.horse.common.gateway.vo.RouteDefinitionVo;
import com.xiaopotian.horse.gateway.service.IRouteConfService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.scheduling.annotation.Async;

import java.net.URI;

/**
 * ==========================================
 * 容器启动后保存配置文件里面的路由信息到Redis
 *
 * @author : 小破天
 * @date : 2019-05-28 18:43
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
@Slf4j
@AllArgsConstructor
@Configuration
public class DynamicRouteInitRunner {
    private final RedisTemplate redisTemplate;
    private final IRouteConfService routeConfService;

    /**
     * 初始化网关路由
     */
    @Async
    @Order
    @EventListener({WebServerInitializedEvent.class, DynamicRouteInitEvent.class})
    public void initRoute() {
        Boolean result = redisTemplate.delete(CommonConstants.ROUTE_KEY);
        log.info("初始化路由网关，result：{}", result);

        routeConfService.list().forEach(u -> {
            RouteDefinitionVo vo = new RouteDefinitionVo();
            vo.setRouteName(u.getRouteName());
            vo.setId(u.getRouteId());
            vo.setUri(URI.create(u.getUri()));
            vo.setOrder(u.getOrder());

            JSONArray filter = JSONUtil.parseArray(u.getFilters());
            vo.setFilters(filter.toList(FilterDefinition.class));

            JSONArray predicate = JSONUtil.parseArray(u.getPredicates());
            vo.setPredicates(predicate.toList(PredicateDefinition.class));

            log.info("加载路由ID：{}，{}", u.getRouteId(), vo);
            redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouteDefinitionVo.class));
            redisTemplate.opsForHash().put(CommonConstants.ROUTE_KEY, u.getRouteId(), vo);
        });

        log.debug("初始化网关路由结束 ");
    }
}
