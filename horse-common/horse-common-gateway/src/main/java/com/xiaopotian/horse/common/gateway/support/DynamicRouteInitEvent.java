package com.xiaopotian.horse.common.gateway.support;

import org.springframework.context.ApplicationEvent;

/**
 * 路由初始化事件
 *
 * @author zouLu
 * @date 2019-04-17 17:21
 **/
public class DynamicRouteInitEvent extends ApplicationEvent {
    public DynamicRouteInitEvent(Object source) {
        super(source);
    }
}

