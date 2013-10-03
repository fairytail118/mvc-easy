/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.mvc.freemarker.tools;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.easy.core.security.util.SecurityUtil;

/**
 * 权限工具
 * 
 * @author wy
 * @version v 0.1 2013-9-27 下午11:09:24 wy Exp $
 */
@Component
public class SecurityTool implements InitializingBean {

    /** 权限前缀 */
    private static String prefixPermission;

    /**
     * 设置默认的权限前缀
     * 
     * @param prefixPermission
     */
    @Value("${easy.permisson.prefix}")
    public void setPrefixPermission(String prefixPermission) {
        SecurityTool.prefixPermission = prefixPermission;
    }

    /**
     * 是否有权限
     * 
     * @param permission
     * @return
     */
    private boolean hasPermission(String permission) {
        Authentication authentication = SecurityUtil.getAuthentication();
        if (authentication == null) {
            return false;
        }
        //如果是超级管理员
        if (SecurityUtil.isSuperAdmin()) {
            return true;
        }

        //权限需要加上前缀

        List<GrantedAuthority> list = SecurityUtil.getUserAuthorities();
        if (list.isEmpty()) {
            return false;
        }
        for (GrantedAuthority ga : list) {
            if (SecurityUtil.getPermission(prefixPermission, permission).equalsIgnoreCase(
                ga.getAuthority())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否有其中任意一个权限
     * 
     * @param privileges 权限码 以逗号隔开
     * @return
     */
    public boolean anyGranted(String privileges) {
        if (StringUtils.isBlank(privileges)) {
            return false;
        }

        String[] prs = StringUtils.split(privileges, ",");
        for (String needPermission : prs) {
            if (hasPermission(needPermission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否有所有权限
     * 
     * @param privileges 权限码 以逗号隔开
     * @return
     */
    public boolean allGranted(String privileges) {
        if (StringUtils.isBlank(privileges)) {
            return false;
        }
        String[] prs = StringUtils.split(privileges, ",");
        int k = prs.length;
        for (String needPermission : prs) {
            if (hasPermission(needPermission)) {
                k--;
                continue;
            }
        }
        return !(k > 0);
    }

    /**
     * 判断是否没有该权限
     * 
     * @param privileges 权限码 以逗号隔开
     * @return
     */
    public boolean notGranted(String privileges) {
        if (StringUtils.isBlank(privileges)) {
            return true;
        }
        return !anyGranted(privileges);
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(prefixPermission), "权限前缀不能为空");
    }

}
