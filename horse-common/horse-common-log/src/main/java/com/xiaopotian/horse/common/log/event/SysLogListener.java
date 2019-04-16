package com.xiaopotian.horse.common.log.event;

import com.xiaopotian.horse.common.core.data.SysLogModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @author lengleng
 * 异步监听日志事件
 */
@Slf4j
@AllArgsConstructor
public class SysLogListener {
    //private final RemoteLogService remoteLogService;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLogModel sysLog = event.getSysLog();
        //remoteLogService.saveLog(sysLog, SecurityConstants.FROM_IN);
        // TODO: 2019-04-11  保存日志
    }
}
