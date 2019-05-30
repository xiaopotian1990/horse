package com.xiaopotian.horse.gateway.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xiaopotian.horse.common.core.constant.CommonConstants;
import com.xiaopotian.horse.common.gateway.vo.RouteDefinitionVo;
import com.xiaopotian.horse.gateway.entity.RouteConf;
import com.xiaopotian.horse.gateway.mapper.RouteConfMapper;
import com.xiaopotian.horse.gateway.service.IRouteConfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 路由配置表 服务实现类
 * </p>
 *
 * @author 小破天
 * @since 2019-05-29
 */
@Service
@AllArgsConstructor
@Slf4j
public class RouteConfServiceImpl extends ServiceImpl<RouteConfMapper, RouteConf> implements IRouteConfService {
    private final RedisTemplate redisTemplate;
    private final ApplicationEventPublisher publisher;

    /**
     * 更新路由信息
     *
     * @param routes 路由信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Mono<Void> updateRoute(List<RouteDefinitionVo> routes) {
        //1、清空redis缓存
        Boolean delete = redisTemplate.delete(CommonConstants.ROUTE_KEY);
        log.info("删除redis网关路由：{}", delete);

        //2、遍历修改的routes，保存到Redis
        routes.forEach(u -> {
            log.info("更新路由->{}", u);

            redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouteDefinitionVo.class));
            redisTemplate.opsForHash().put(CommonConstants.ROUTE_KEY, u.getId(), u);
        });
        //3、逻辑删除全部
        this.remove(new UpdateWrapper<>());

        //4、插入生效路由
        List<RouteConf> routeConfList = routes.stream().map(vo -> {
            RouteConf routeConf = new RouteConf();
            routeConf.setRouteId(vo.getId());
            routeConf.setRouteName(vo.getRouteName());
            routeConf.setFilters(JSONUtil.toJsonStr(vo.getFilters()));
            routeConf.setPredicates(JSONUtil.toJsonStr(vo.getPredicates()));
            routeConf.setOrder(vo.getOrder());
            routeConf.setUri(vo.getUri().toString());
            return routeConf;
        }).collect(Collectors.toList());
        this.saveBatch(routeConfList);
        log.debug("更新网关路由结束 ");

        //5、异步事件
        this.publisher.publishEvent(new RefreshRoutesEvent(this));

        return Mono.empty();
    }
}
