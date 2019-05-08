package com.xiaopotian.horse.common.log.event;

import com.xiaopotian.horse.common.core.data.SysLogModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ==========================================
 * 系统日志事件
 *
 * @author : 小破天
 * @date : 2019-04-11
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
@Getter
@AllArgsConstructor
public class SysLogEvent {
    private final SysLogModel sysLog;
}
