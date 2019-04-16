package com.xiaopotian.horse.common.log.event;

import com.xiaopotian.horse.common.core.data.SysLogModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lengleng
 * 系统日志事件
 */
@Getter
@AllArgsConstructor
public class SysLogEvent {
    private final SysLogModel sysLog;
}
