package com.xiaopotian.horse.gateway.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 路由配置表
 * </p>
 *
 * @author 小破天
 * @since 2019-05-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_route_conf")
public class RouteConf implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 路由名称
     */
    private String routeName;

    /**
     * 路由ID
     */
    private String routeId;

    /**
     * 断言
     */
    private String predicates;

    /**
     * 过滤器
     */
    private String filters;

    private String uri;

    /**
     * 排序
     */
    @TableField("`order`")
    private Integer order;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 乐观锁
     */
    @Version
    private Integer version;

    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;


}
