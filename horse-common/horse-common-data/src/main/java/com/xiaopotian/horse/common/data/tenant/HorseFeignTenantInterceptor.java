package com.xiaopotian.horse.common.data.tenant;

import com.xiaopotian.horse.common.core.constant.CommonConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * ==========================================
 * 用Feign客户端RequestInterceptor转发请求标头或安全上下文
 *
 * @author : 小破天
 * @date : 2019-05-29 20:09
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
@Slf4j
public class HorseFeignTenantInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String tenantId = TenantContextHolder.getTenantId();
        if (tenantId == null) {
            log.error("TTL 中的 租户ID为空，feign拦截器 >> 增强失败");
            return;
        }

        requestTemplate.header(CommonConstants.TENANT_ID, tenantId);
    }
}
