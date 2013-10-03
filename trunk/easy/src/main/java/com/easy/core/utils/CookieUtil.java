/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.utils;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easy.core.common.Constants;

/**
 * cookie操作
 * 
 * @author wy
 * @version v 0.1 2013-9-14 下午12:15:25 wy Exp $
 */
public class CookieUtil {

    /** 日志 */
    private final static Logger log      = LoggerFactory.getLogger(CookieUtil.class);

    /** 查询条件的前缀 */
    public final static String  CRITERIA = "_criteria_";

    /**
     * 增加查询条件到cookie
     * 
     * @param request
     * @param response
     */
    public static void addCriteria(HttpServletRequest request, HttpServletResponse response) {

        // 请求路径
        String url = request.getRequestURI();

        // 非列表页面不做处理
        if (StringUtils.indexOf(url, "_list") == -1) {
            return;
        }

        // 参数
        @SuppressWarnings("unchecked")
        Enumeration<String> e = request.getParameterNames();
        // 获取表单参数
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        while (e != null && e.hasMoreElements()) {
            String name = e.nextElement();
            String[] values = request.getParameterValues(name);
            if (values == null || values.length == 0) {
                paramsMap.put(name, "");
            } else if (values.length == 1) {
                paramsMap.put(name, values[0]);
            } else {
                paramsMap.put(name, values);
            }
        }

        try {
            String paramJson = new ObjectMapper().writeValueAsString(paramsMap);
            addCookie(request, response, CRITERIA + url,
                URLEncoder.encode(paramJson, Constants.UTF8), -1, "");
        }
        catch (IOException ex) {
            log.error("设置查询条件时转换request的表单参数出错", ex);
        }
        catch (Exception ex) {
            log.error("设置查询条件的cookie出错", ex);
        }
    }

    /**
     * 增加查询条件到cookie
     * 
     * @param request
     * @param response
     * @return
     */
    public static Map<String, Object> getCriteria(HttpServletRequest request) {

        // 获取表单参数
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        // 请求路径
        String url = request.getRequestURI();

        // 非列表页面不做处理
        if (StringUtils.indexOf(url, "_list") == -1) {
            return paramsMap;
        }

        try {
            Cookie cookie = getCookie(request, CRITERIA + url);
            if (cookie == null) {
                return paramsMap;
            }

            String value = cookie.getValue();
            if (StringUtils.isBlank(value)) {
                return paramsMap;
            }

            String json = URLDecoder.decode(value, Constants.UTF8);
            return new ObjectMapper().readValue(json, new TypeReference<HashMap<String, String>>() {
            });

        }
        catch (IOException ex) {
            log.error("转换request的表单参数出错", ex);
        }
        catch (Exception ex) {
            log.error("转换查询条件的cookie出错", ex);
        }
        return paramsMap;
    }

    /**
     * 获得cookie
     * 
     * @param request HttpServletRequest
     * @param name cookie name
     * @return if exist return cookie, else return null.
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    return c;
                }
            }
        }
        return null;
    }

    /**
     * 根据部署路径，将cookie保存在根目录。
     * 
     * @param request
     * @param response
     * @param name
     * @param value
     * @param expiry
     * @param domain
     * @return
     */
    public static Cookie addCookie(HttpServletRequest request, HttpServletResponse response,
                                   String name, String value, Integer expiry, String domain) {
        Cookie cookie = new Cookie(name, value);
        if (expiry != null) {
            cookie.setMaxAge(expiry);
        }
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        String ctx = request.getContextPath();
        cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
        response.addCookie(cookie);
        return cookie;
    }

    /**
     * 取消cookie
     * 
     * @param request
     * @param response
     * @param name
     * @param domain
     */
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response,
                                    String name, String domain) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        String ctx = request.getContextPath();
        cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        response.addCookie(cookie);
    }
}
