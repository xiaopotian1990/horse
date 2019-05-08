package com.xiaopotian.horse.common.log.annotation;

import java.lang.annotation.*;

/**
 * ==========================================
 * 操作日志注解
 *
 * @author : 小破天
 * @date : 2019-04-11
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    /**
     * 描述
     *
     * @return {String}
     */
    String value();
}
