/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.weixin.filter.handler;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 总的请求处理
 * 
 * @author wy
 * @version v 0.1 2013-11-20 下午8:23:55 wy Exp $
 */
public interface RequestHandler {

    /**
     * 处理请求
     * 
     * @param request
     * @param response
     * @param filterChain
     */
    public void handleRequest(HttpServletRequest request, HttpServletResponse response,
                              FilterChain filterChain);

}
