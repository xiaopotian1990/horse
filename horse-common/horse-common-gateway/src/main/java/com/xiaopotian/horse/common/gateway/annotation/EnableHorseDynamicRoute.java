package com.xiaopotian.horse.common.gateway.annotation;

import com.xiaopotian.horse.common.gateway.configuration.DynamicRouteAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created By Idea
 *
 * @author zouLu
 * @date 2019-04-17 17:08
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(DynamicRouteAutoConfiguration.class)
public @interface EnableHorseDynamicRoute {
}
