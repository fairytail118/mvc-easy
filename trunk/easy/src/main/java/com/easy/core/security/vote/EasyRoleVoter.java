/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.security.vote;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.easy.admin.entity.User;
import com.easy.core.security.util.SecurityUtil;

/**
 * 自定义的角色访问
 * 
 * @author wy
 * @version v 0.1 2013-10-4 下午1:29:42 wy Exp $
 */
public class EasyRoleVoter implements AccessDecisionVoter {

    /** 日志 */
    private final static Logger log = LoggerFactory.getLogger(EasyRoleVoter.class);

    /**
     * @see org.springframework.security.access.AccessDecisionVoter#supports(org.springframework.security.access.ConfigAttribute)
     */
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return attribute != null;
    }

    /**
     * @see org.springframework.security.access.AccessDecisionVoter#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * @see org.springframework.security.access.AccessDecisionVoter#vote(org.springframework.security.core.Authentication,
     *      java.lang.Object, java.util.Collection)
     */
    @Override
    public int vote(Authentication authentication, Object object,
                    Collection<ConfigAttribute> attributes) {
        Collection<GrantedAuthority> authorities = extractAuthorities(authentication);

        if (attributes == null || attributes.isEmpty()) {
            return ACCESS_GRANTED;
        }

        log.debug("被访问资源URL={}", object.toString());

        Object obj = authentication.getPrincipal();
        if (obj != null && obj instanceof User) {
            //如果是超级管理员
            if (SecurityUtil.isSuperAdmin((User) obj)) {
                return ACCESS_GRANTED;
            }
        }

        // configAttributes是含有访问url的所有权限   
        // authentication.getAuthorities()用户的权限或者角色   
        Iterator<ConfigAttribute> it = attributes.iterator();
        int result = ACCESS_ABSTAIN;
        //现在定义的是或关系
        while (it.hasNext()) {
            ConfigAttribute configAttribute = it.next();
            if (this.supports(configAttribute)) {
                result = ACCESS_DENIED;
                String needRole = configAttribute.getAttribute();
                //当url的所需角色，也是这个账户拥有的角色一样的时候，表示可以访问   
                for (GrantedAuthority ga : authorities) {
                    //1、是系统管理员并且是系统权限 直接放开
                    //2、 具有该链接的访问权限
                    if (needRole.equalsIgnoreCase(ga.getAuthority())) {
                        return ACCESS_GRANTED;
                    }
                }
            }
        }
        return result;

    }

    /**
     * 
     * 
     * @param authentication
     * @return
     */
    Collection<GrantedAuthority> extractAuthorities(Authentication authentication) {
        return authentication.getAuthorities();
    }

}
