/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.security;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 访问决策器，决定某个用户具有的角色，是否有足够的权限去访问某个资源
 * 
 * @author wy
 * @version v 0.1 2013-9-24 下午11:04:28 wy Exp $
 */
public class EasyAccessDecisionManager extends AbstractAccessDecisionManager {

    private final static Logger LOG = LoggerFactory.getLogger(EasyAccessDecisionManager.class);

    @Override
    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
                                                                    InsufficientAuthenticationException {
        if (configAttributes == null || configAttributes.isEmpty()) {
            return;
        }
        LOG.debug("被访问资源URL={}", object.toString());

        // configAttributes是含有访问url的所有权限   
        // authentication.getAuthorities()用户的权限或者角色   
        Iterator<ConfigAttribute> it = configAttributes.iterator();
        //现在定义的是或关系
        while (it.hasNext()) {
            ConfigAttribute ca = it.next();
            String needRole = ca.getAttribute();
            //当url的所需角色，也是这个账户拥有的角色一样的时候，表示可以访问   
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                //1、是系统管理员并且是系统权限 直接放开
                //2、 具有该链接的访问权限
                if (needRole.equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        //没有权限就抛出异常.   
        throw new AccessDeniedException("没有访问权限.");
    }

}
