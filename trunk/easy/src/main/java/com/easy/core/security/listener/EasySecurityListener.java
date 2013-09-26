/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.security.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;

import com.easy.core.security.EasyWebAuthenticationDetails;

/**
 * 监听器 - 后台登录成功、登录失败事件监听处理
 * 
 * @author wy
 * @version v 0.1 2013-9-24 下午11:04:28 wy Exp $
 */
@Component
public class EasySecurityListener implements ApplicationListener<ApplicationEvent> {

    private final static Logger log = LoggerFactory.getLogger(EasySecurityListener.class);

    public void onApplicationEvent(ApplicationEvent event) {
        // 登录成功
        if (event instanceof AuthenticationSuccessEvent) {
            AuthenticationSuccessEvent authEvent = (AuthenticationSuccessEvent) event;
            Authentication authentication = (Authentication) authEvent.getSource();

            Object object = authentication.getDetails();
            String loginUsername = authentication.getName();
            //获取接口返回的用户实体对象 authentication.getPrincipal();
            if (object instanceof EasyWebAuthenticationDetails) {
                EasyWebAuthenticationDetails twad = (EasyWebAuthenticationDetails) object;
                // twad.getRemoteAddress();
                //twad.getClientBrowser();
                //twad.getClientOs();

            } else if (object instanceof WebAuthenticationDetails) {
                WebAuthenticationDetails wad = (WebAuthenticationDetails) object;
                //wad.getRemoteAddress();

            }
        }

        // 登录失败
        else if (event instanceof AuthenticationFailureBadCredentialsEvent) {
            AuthenticationFailureBadCredentialsEvent authEvent = (AuthenticationFailureBadCredentialsEvent) event;
            Authentication authentication = (Authentication) authEvent.getSource();
            Object object = authentication.getDetails();
            String loginUsername = authentication.getName();
            // TODO 需要记录失败次数，对超过设定次数的账号进行锁定
            if (object instanceof EasyWebAuthenticationDetails) {
                EasyWebAuthenticationDetails twad = (EasyWebAuthenticationDetails) object;

            } else if (object instanceof WebAuthenticationDetails) {
                WebAuthenticationDetails wad = (WebAuthenticationDetails) object;

            }
        }

        // 登出判断
        else if (event instanceof HttpSessionDestroyedEvent) {
            SecurityContext securityContext = ((HttpSessionDestroyedEvent) event)
                .getSecurityContext();
            if (securityContext != null) {
                Authentication authentication = securityContext.getAuthentication();
                String name = authentication.getName();
                Object object = authentication.getDetails();
                if (object instanceof EasyWebAuthenticationDetails) {
                } else if (object instanceof WebAuthenticationDetails) {
                }
            }

        }

    }
}
