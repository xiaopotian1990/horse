package com.xiaopotian.horse.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * ==========================================
 * 服务中心
 * User: 小破天
 * Date: 2019-04-04
 * Time: 22:45
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
@EnableEurekaServer
@SpringBootApplication
public class HorseEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(HorseEurekaApplication.class, args);
    }
}
