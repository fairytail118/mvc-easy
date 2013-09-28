/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.security.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.easy.admin.entity.User;
import com.easy.admin.enums.UserType;
import com.easy.core.enums.EnumsUtil;

/**
 * 权限判断的工具类
 * 
 * @author wy
 * @version v 0.1 2013-9-24 下午11:04:28 wy Exp $
 */
public class SecurityUtil {

    /**
     * 获取当前的用户认证
     * 
     * @return
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 判断是否超级管理员
     * 
     * @return
     */
    public static boolean isSuperAdmin() {
        User user = getLoginUser();
        if (user == null) {
            return false;
        }
        return isSuperAdmin(user);
    }

    /**
     * 该用户是否超级管理员
     * 
     * @param user
     * @return
     */
    public static boolean isSuperAdmin(User user) {
        UserType userType = EnumsUtil.valueOfIgnoreCase(UserType.class, user.getUserType());
        //如果是超级管理员
        return (userType != null && userType == UserType.SUPER_ADMIN);
    }

    /**
     * 获取当前登录的用户
     * 
     * @param request
     * @return
     */
    public static User getLoginUser() {

        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object obj = authentication.getPrincipal();
        if (obj != null && obj instanceof User) {
            return (User) obj;
        }
        return null;
    }

    /**
     * 获取权限列表
     * 
     * @return
     */
    public static List<GrantedAuthority> getUserAuthorities() {
        Authentication authentication = getAuthentication();

        if (authentication == null) {
            return new ArrayList<GrantedAuthority>();
        }

        Collection<GrantedAuthority> auths = authentication.getAuthorities();
        if (auths == null) {
            return new ArrayList<GrantedAuthority>();
        }
        return new ArrayList<GrantedAuthority>(auths);
    }

    /**
     * 获取权限的url
     * 
     * @param resUrl
     */
    public static String getUrl(String resUrl) {

        //加上斜杠前缀
        resUrl = resUrl.startsWith("/") ? resUrl : ("/" + resUrl);

        if (resUrl.indexOf('*') != -1) {
            return resUrl;
        }

        return resUrl + "*";
    }

    /**
     * 权限码以','切分
     * 
     * @param prefixPermission
     * @param permission
     * @return
     */
    public static String[] getPermissions(String prefixPermission, String permission) {
        String[] permissions = permission.split(",");
        for (int i = 0; i < permissions.length; i++) {
            permissions[i] = getPermission(prefixPermission, permissions[i]);
        }
        return permissions;
    }

    /**
     * 权限码转换
     * 
     * @param prefixPermission
     * @param permission
     * @return
     */
    public static String getPermission(String prefixPermission, String permission) {
        if (permission.trim().toUpperCase().startsWith(prefixPermission.toUpperCase())) {
            return permission.trim().toUpperCase();
        } else {
            return (prefixPermission + permission.trim()).toUpperCase();
        }
    }

}
