/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.weixin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easy.weixin.filter.handler.RequestHandler;

/**
 * 微信请求 <li>请求的数据结构如下 <li>/weixin/token_map token对应的请求映射/ <li>
 * 
 * @author wy
 * @version v 0.1 2013-11-19 下午10:03:51 wy Exp $
 */
public class WeixinFilter implements Filter {

    private RequestHandler requestHandler;

    /**
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
                                                                                           throws IOException,
                                                                                           ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        requestHandler.handleRequest(request, response, filterChain);
    }

    /**
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {

    }

    /**
     * Setter method for property <tt>requestHandler</tt>.
     * 
     * @param requestHandler value to be assigned to property requestHandler
     */
    public void setRequestHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

}
