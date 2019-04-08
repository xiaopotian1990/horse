package com.xiaopotian.horse.config;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * ==========================================
 * 配置中心
 * User: 小破天
 * Date: 2019-04-04
 * Time: 23:05
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
@EnableConfigServer
@SpringCloudApplication
public class HorseConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(HorseConfigApplication.class, args);
    }
}
