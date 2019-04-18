package com.xiaopotian.horse.common.gateway.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.io.Serializable;

/**
 * 扩展此类支持序列化
 *
 * @author zouLu
 * @date 2019-04-17 17:02
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class RouteDefinitionVo extends RouteDefinition implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 路由名称
     */
    private String routeName;
}
