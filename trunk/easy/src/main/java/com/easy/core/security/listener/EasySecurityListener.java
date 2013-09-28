/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.security.listener;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;

import com.easy.admin.entity.LoginLog;
import com.easy.admin.entity.User;
import com.easy.admin.enums.LoginLogAction;
import com.easy.admin.enums.LoginLogStatus;
import com.easy.admin.service.LoginLogService;
import com.easy.core.security.EasyWebAuthenticationDetails;

/**
 * 监听器 - 后台登录成功、登录失败事件监听处理
 * 
 * @author wy
 * @version v 0.1 2013-9-24 下午11:04:28 wy Exp $
 */
@Component
public class EasySecurityListener implements ApplicationListener<ApplicationEvent> {

    private final static Logger logger = LoggerFactory.getLogger(EasySecurityListener.class);

    @Autowired
    private LoginLogService     loginLogService;

    /**
     * 事件处理
     * 
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    public void onApplicationEvent(ApplicationEvent event) {
        // 登录成功
        if (event instanceof AuthenticationSuccessEvent) {
            AuthenticationSuccessEvent authEvent = (AuthenticationSuccessEvent) event;
            Authentication authentication = (Authentication) authEvent.getSource();

            Object object = authentication.getDetails();
            String loginUsername = authentication.getName();
            User user = (User) authentication.getPrincipal();
            Date date = new Date();
            LoginLog log = new LoginLog();
            if (object instanceof EasyWebAuthenticationDetails) {
                EasyWebAuthenticationDetails twad = (EasyWebAuthenticationDetails) object;
                log.setClientIp(twad.getRemoteAddress());
                log.setClientBrowser(twad.getClientBrowser());
                log.setClientOs(twad.getClientOs());

            } else if (object instanceof WebAuthenticationDetails) {
                WebAuthenticationDetails wad = (WebAuthenticationDetails) object;
                log.setClientIp(wad.getRemoteAddress());

            }
            StringBuffer content = new StringBuffer();
            content.append("【").append(user.getName()).append("】")
                .append(LoginLogAction.LOGIN.getCode()).append("系统").append("成功");
            log.setContent(content.toString());
            log.setAction(LoginLogAction.LOGIN.getCode());
            log.setCreateTime(date);
            log.setModifyUser(user.getUsername());
            log.setCreateUser(user.getUsername());
            log.setModifyTime(date);
            log.setLoginTime(date);
            log.setStatus(LoginLogStatus.SUCCEED.getCode());
            log.setUsername(loginUsername);
            loginLogService.save(log);
            logger.debug("用户{}成功登录!,IP:{}", loginUsername, log.getClientIp());
        }

        // 登录失败
        else if (event instanceof AuthenticationFailureBadCredentialsEvent) {
            AuthenticationFailureBadCredentialsEvent authEvent = (AuthenticationFailureBadCredentialsEvent) event;
            Authentication authentication = (Authentication) authEvent.getSource();
            Object object = authentication.getDetails();
            String loginUsername = authentication.getName();
            Date date = new Date();
            LoginLog log = new LoginLog();
            if (object instanceof EasyWebAuthenticationDetails) {
                EasyWebAuthenticationDetails twad = (EasyWebAuthenticationDetails) object;
                log.setClientIp(twad.getRemoteAddress());
                log.setClientBrowser(twad.getClientBrowser());
                log.setClientOs(twad.getClientOs());

            } else if (object instanceof WebAuthenticationDetails) {
                WebAuthenticationDetails wad = (WebAuthenticationDetails) object;
                log.setClientIp(wad.getRemoteAddress());

            }
            StringBuffer content = new StringBuffer();
            content.append("【").append(loginUsername).append("】")
                .append(LoginLogAction.LOGIN.getCode()).append("系统").append("失败");
            log.setContent(content.toString());
            log.setAction(LoginLogAction.LOGIN.getCode());
            log.setCreateTime(date);
            log.setCreateUser(null);
            log.setModifyUser(null);
            log.setModifyTime(date);
            log.setLoginTime(date);
            log.setStatus(LoginLogStatus.ABORTED.getCode());
            log.setUsername(loginUsername);
            loginLogService.save(log);
            logger.debug("用户{}登录失败!,IP:{}", loginUsername, log.getClientIp());
        }

        // 登出判断
        else if (event instanceof HttpSessionDestroyedEvent) {
            SecurityContext securityContext = ((HttpSessionDestroyedEvent) event)
                .getSecurityContext();
            if (securityContext != null) {
                Authentication authentication = securityContext.getAuthentication();
                User user = (User) authentication.getPrincipal();
                String loginUsername = authentication.getName();
                Date date = new Date();
                Object object = authentication.getDetails();
                LoginLog log = new LoginLog();
                if (object instanceof EasyWebAuthenticationDetails) {
                    EasyWebAuthenticationDetails details = (EasyWebAuthenticationDetails) object;
                    log.setClientIp(details.getRemoteAddress());
                    log.setClientBrowser(details.getClientBrowser());
                    log.setClientOs(details.getClientOs());
                } else if (object instanceof WebAuthenticationDetails) {
                    WebAuthenticationDetails wad = (WebAuthenticationDetails) object;
                    log.setClientIp(wad.getRemoteAddress());
                }
                StringBuffer content = new StringBuffer();
                content.append("【").append(user.getName()).append("】")
                    .append(LoginLogAction.LOGOUT.getDesc()).append("系统").append("成功");
                log.setContent(content.toString());
                log.setAction(LoginLogAction.LOGOUT.getCode());
                log.setCreateTime(date);
                log.setModifyUser(user.getUsername());
                log.setCreateUser(user.getUsername());
                log.setModifyTime(date);
                log.setLoginTime(date);
                log.setStatus(LoginLogStatus.SUCCEED.getCode());
                log.setUsername(loginUsername);
                loginLogService.save(log);
                logger.debug("用户{}退出登录!,IP:{}", loginUsername, log.getClientIp());
            }

        }

    }
}
