package com.xiaopotian.horse.gateway;

import com.xiaopotian.horse.common.gateway.annotation.EnableHorseDynamicRoute;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * ==========================================
 * Gateway网关应用
 * @author : 小破天
 * @date : 2019-05-17 17:10
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
@SpringCloudApplication
@EnableHorseDynamicRoute
public class HorseGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(HorseGatewayApplication.class, args);
    }
}
