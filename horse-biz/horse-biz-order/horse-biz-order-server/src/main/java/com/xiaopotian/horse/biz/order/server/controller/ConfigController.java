package com.xiaopotian.horse.biz.order.server.controller;

import com.xiaopotian.horse.biz.order.server.config.HorseConfigProperties;
import com.xiaopotian.horse.biz.order.server.view.ConfigVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ==========================================
 * Created with IntelliJ IDEA.
 * User: 小破天
 * Date: 2019-04-05
 * Time: 20:14
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
@RestController
@Slf4j
public class ConfigController {
    @Autowired
    private HorseConfigProperties config;

    @GetMapping("/config")
    public ConfigVM config() {
        log.info("name:{};age:{}", config.getName(), config.getAge());
        return new ConfigVM(config.getName(), config.getAge());
    }
}
