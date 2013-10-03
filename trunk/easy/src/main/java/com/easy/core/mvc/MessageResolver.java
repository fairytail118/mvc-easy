/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.mvc;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * 获取国际化资源文件
 * 
 * @author wy
 * @version v 0.1 2013-9-16 上午7:57:11 wy Exp $
 */
public final class MessageResolver {

    private MessageResolver() {

    }

    /**
     * 获得国际化信息
     * 
     * @param request HttpServletRequest
     * @param code 国际化代码
     * @param args 替换参数
     * @return
     * @see org.springframework.context.MessageSource#getMessage(String,
     *      Object[], Locale)
     */
    public static String getMessage(HttpServletRequest request, String code, Object... args) {
        WebApplicationContext webContext = RequestContextUtils.getWebApplicationContext(request);
        if (webContext == null) {
            throw new IllegalStateException("WebApplicationContext not found!");
        }
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        Locale locale;
        if (localeResolver != null) {
            locale = localeResolver.resolveLocale(request);
        } else {
            locale = request.getLocale();
        }
        return webContext.getMessage(code, args, locale);
    }
}
