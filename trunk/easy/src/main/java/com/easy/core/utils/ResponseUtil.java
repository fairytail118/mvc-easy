/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 输出工具
 * 
 * @author wy
 * @version v 0.1 2013-11-20 下午8:45:15 wy Exp $
 */
public class ResponseUtil {

    private final static Logger log = LoggerFactory.getLogger(ResponseUtil.class);

    /**
     * 直接输出内容的简便函数.
     * 
     * @param response
     * @param contentType
     * @param content
     */
    public static void write(HttpServletResponse response, String contentType, String content) {
        try {
            response.setContentType(contentType);
            //不缓存
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.getWriter().write(content);
            response.getWriter().flush();
        }
        catch (IOException e) {
            log.error("response Io流异常", e);
        }
    }

    /**
     * 文本输出
     * 
     * @param response
     * @param text
     */
    public static void writeText(HttpServletResponse response, String text) {
        write(response, "text/plain;charset=UTF-8", text);
    }

    /**
     * html输出
     * 
     * @param response
     * @param text
     */
    public static void writeHtml(HttpServletResponse response, String html) {
        write(response, "text/html;charset=UTF-8", html);
    }

    /**
     * xml输出
     * 
     * @param response
     * @param xml
     */
    public static void writeXml(HttpServletResponse response, String xml) {
        write(response, "text/xml;charset=UTF-8", xml);
    }

}
