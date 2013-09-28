/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.mvc.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.easy.core.mvc.result.Result;
import com.easy.core.security.util.SecurityUtil;

/**
 * 登录拦截
 * 
 * @author wy
 * @version v 0.1 2013-6-18 上午7:58:24 wy Exp $
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    /** 日志 */
    private final static Logger log = LoggerFactory.getLogger(AuthInterceptor.class);

    private List<String>        excludeUrls;

    /** 登录表单地址 */
    private String              loginFormUrl;

    /**
     * Setter method for property <tt>excludeUrls</tt>.
     * 
     * @param excludeUrls
     *            value to be assigned to property excludeUrls
     */
    public void setExcludeUrls(List<String> excludeUrls) {
        this.excludeUrls = excludeUrls;
    }

    /**
     * Setter method for property <tt>loginFormUrl</tt>.
     * 
     * @param loginFormUrl
     *            value to be assigned to property loginFormUrl
     */
    public void setLoginFormUrl(String loginFormUrl) {
        this.loginFormUrl = loginFormUrl;
    }

    /**
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        String path = request.getServletPath();
        if (excludeUrls != null && excludeUrls.contains(path)) {
            log.debug("登录拦截排除路径{}", path);
            return true;
        }
        //如果未登录 
        if (SecurityUtil.getLoginUser() == null) {
            String requestWith = request.getHeader("X-Requested-With");
            // 如果是ajax请求
            if (StringUtils.endsWithIgnoreCase("XMLHttpRequest", requestWith)) {
                Result result = new Result("无权限操作");
                result.setDenied(true);
                response.getWriter().write(new ObjectMapper().writeValueAsString(result));
            } else {
                response.sendRedirect(request.getContextPath() + loginFormUrl);
            }
            return false;
        }
        return true;
    }

    /**
     * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
    }

}
