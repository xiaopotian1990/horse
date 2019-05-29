package com.xiaopotian.horse.common.data.tenant;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;

/**
 * ==========================================
 * 租户工具类
 *
 * @author : 小破天
 * @date : 2019-05-29 19:37
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
@UtilityClass
public class TenantContextHolder {
    private final ThreadLocal<String> THREAD_LOCAL_TENANT = new TransmittableThreadLocal<>();

    /**
     * TTL 设置租户ID
     *
     * @param tenantId
     */
    void setTenantId(String tenantId) {
        THREAD_LOCAL_TENANT.set(tenantId);
    }

    /**
     * 获取TTL中的租户ID
     *
     * @return
     */
    public String getTenantId() {
        return THREAD_LOCAL_TENANT.get();
    }

    public void clear() {
        THREAD_LOCAL_TENANT.remove();
    }
}
