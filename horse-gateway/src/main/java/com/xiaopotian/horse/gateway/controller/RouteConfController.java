package com.xiaopotian.horse.gateway.controller;


import com.xiaopotian.horse.common.core.util.R;
import com.xiaopotian.horse.common.gateway.vo.RouteDefinitionVo;
import com.xiaopotian.horse.common.log.annotation.SysLog;
import com.xiaopotian.horse.gateway.service.IRouteConfService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 路由配置表 前端控制器
 * </p>
 *
 * @author 小破天
 * @since 2019-05-29
 */
@RestController
@RequestMapping("/route")
@AllArgsConstructor
public class RouteConfController {
    private final IRouteConfService routeConfService;

    /**
     * 获取当前定义的路由信息
     *
     * @return R
     */
    @PostMapping("/list")
    public R list() {
        return new R<>(routeConfService.list());
    }

    /**
     * 修改路由
     * @param routes 路由信息
     * @return R
     */
    @SysLog("修改路由")
    @PostMapping("/update")
    public R update(@RequestBody List<RouteDefinitionVo> routes){
        return new R<>(routeConfService.updateRoute(routes));
    }
}

