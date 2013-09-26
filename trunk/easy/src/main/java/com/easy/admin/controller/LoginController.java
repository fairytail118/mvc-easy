/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.easy.core.controller.BaseController;
import com.easy.core.security.LoginStatus;
import com.easy.core.security.exception.CodeAuthenticationServiceException;
import com.easy.core.utils.RequestUtil;

/**
 * 登录
 * 
 * @author wy
 * @version v 0.1 2013-9-24 下午10:57:06 wy Exp $
 */
@Controller
public class LoginController extends BaseController {

    /**
     * 用户登录
     * 
     * @param request
     * @param status
     * @param model
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request,
                        @RequestParam(value = "status", required = false) String status,
                        ModelMap model) {
        if (StringUtils.isNotBlank(status)) {
            LoginStatus loginStatus = LoginStatus.getLoginStatus(status);
            if (loginStatus != null) {
                model.put("message", loginStatus.getName());
            }
        }

        HttpSession session = RequestUtil.getSession(request);

        //最后一次登录的用户名
        String lastLoginName = (String) session.getAttribute(WebAttributes.LAST_USERNAME);
        model.put("username", lastLoginName);
        session.removeAttribute(WebAttributes.LAST_USERNAME);

        Exception springSecurityLastException = (Exception) session
            .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        if (springSecurityLastException != null) {
            if (springSecurityLastException instanceof BadCredentialsException) {
                model.put("message", "用户名或者密码错误");
            } else if (springSecurityLastException instanceof DisabledException) {
                model.put("message", "您的账号已被禁用,无法登录!");
            } else if (springSecurityLastException instanceof LockedException) {
                model.put("message", "您的账号已被锁定,无法登录!");
            } else if (springSecurityLastException instanceof AccountExpiredException) {
                model.put("message", "您的账号已过期,无法登录!");
            } 
            else if(springSecurityLastException instanceof CodeAuthenticationServiceException){
                model.put("message", springSecurityLastException.getMessage());
            }
            else if (springSecurityLastException instanceof AuthenticationServiceException) {
                String message = springSecurityLastException.getMessage();
                if (message != null && message.contains("Connection")) {
                    model.put("message", "数据库连接异常");
                } else {
                    model.put("message", "未知错误,无法登录!");
                }
            } else if (springSecurityLastException instanceof DataAccessException) {
                model.put("message", "数据库查询异常!");
            } else {
                model.put("message", "出现未知错误,无法登录!");
            }
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }

        return "login";
    }
}
