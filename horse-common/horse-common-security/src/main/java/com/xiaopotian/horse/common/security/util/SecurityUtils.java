package com.xiaopotian.horse.common.security.util;

import cn.hutool.core.util.StrUtil;
import com.xiaopotian.horse.common.core.constant.SecurityConstants;
import com.xiaopotian.horse.common.security.service.HorseUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * ==========================================
 * 安全工具类
 * @author : 小破天
 * @date : 2019-05-29 21:04
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
@UtilityClass
public class SecurityUtils {
    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     *
     * @param authentication
     * @return PigxUser
     * <p>
     * 获取当前用户的全部信息 EnablePigxResourceServer true
     * 获取当前用户的用户名 EnablePigxResourceServer false
     */
    public HorseUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof HorseUser) {
            return (HorseUser) principal;
        }
        return null;
    }

    /**
     * 获取用户
     */
    public HorseUser getUser() {
        Authentication authentication = getAuthentication();
        return getUser(authentication);
    }

    /**
     * 获取用户角色信息
     *
     * @return 角色集合
     */
    public List<Integer> getRoles() {
        Authentication authentication = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<Integer> roleIds = new ArrayList<>();
        authorities.stream()
                .filter(granted -> StrUtil.startWith(granted.getAuthority(), SecurityConstants.ROLE))
                .forEach(granted -> {
                    String id = StrUtil.removePrefix(granted.getAuthority(), SecurityConstants.ROLE);
                    roleIds.add(Integer.parseInt(id));
                });
        return roleIds;
    }

}
