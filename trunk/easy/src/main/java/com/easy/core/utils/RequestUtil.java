/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.easy.core.common.Constants;

/**
 * request的操作
 * 
 * @author wy
 * @version v 0.1 2013-9-10 下午9:26:44 wy Exp $
 */
public class RequestUtil {

    /** 表单的错误 */
    public final static String FORM_ERROR = "formErros";

    /**
     * 获取访问者IP
     * 
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * 
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddr()。
     * 
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {

        /**
         * request = ((ServletRequestAttributes) RequestContextHolder
         * .getRequestAttributes()).getRequest();
         */
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

    /**
     * 设置表单的错误
     * 
     * @param request
     * @param map
     */
    public static void setFormErrors(HttpServletRequest request, Map<String, String> map) {

        if (map != null && !map.isEmpty()) {
            request.setAttribute(FORM_ERROR, map);
        }

    }

    /**
     * 获取表单的错误信息
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getFormErrors(HttpServletRequest request) {
        Map<String, String> map = (Map<String, String>) request.getAttribute(FORM_ERROR);
        if (map == null) {
            map = new LinkedHashMap<String, String>();
        }

        return map;
    }

    /**
     * 是否有表单错误
     * 
     * @param request
     * @return
     */
    public static boolean hasErrors(HttpServletRequest request) {
        return !getFormErrors(request).isEmpty();
    }

    /**
     * 获得请求的session id，但是HttpServletRequest#getRequestedSessionId()方法有一些问题。
     * 当存在部署路径的时候，会获取到根路径下的jsessionid。
     * 
     * @see HttpServletRequest#getRequestedSessionId()
     * 
     * @param request
     * @return
     */
    public static String getRequestedSessionId(HttpServletRequest request) {
        String sid = request.getRequestedSessionId();
        String ctx = request.getContextPath();
        // 如果session id是从url中获取，或者部署路径为空，那么是在正确的。
        if (request.isRequestedSessionIdFromURL() || StringUtils.isBlank(ctx)) {
            return sid;
        } else {
            // 手动从cookie获取
            Cookie cookie = CookieUtil.getCookie(request, Constants.JSESSION_COOKIE);
            if (cookie != null) {
                return cookie.getValue();
            } else {
                return null;
            }
        }

    }
}
