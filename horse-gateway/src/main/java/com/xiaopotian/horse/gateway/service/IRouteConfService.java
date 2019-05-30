package com.xiaopotian.horse.gateway.service;

import cn.hutool.json.JSONArray;
import com.xiaopotian.horse.common.gateway.vo.RouteDefinitionVo;
import com.xiaopotian.horse.gateway.entity.RouteConf;
import com.baomidou.mybatisplus.extension.service.IService;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <p>
 * 路由配置表 服务类
 * </p>
 *
 * @author 小破天
 * @since 2019-05-29
 */
public interface IRouteConfService extends IService<RouteConf> {
    /**
     * 更新路由信息
     *
     * @param routes 路由信息
     * @return
     */
    Mono<Void> updateRoute(List<RouteDefinitionVo> routes);
}
