/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.weixin.filter.handler;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easy.core.utils.ResponseUtil;
import com.easy.weixin.token.RequestToken;
import com.easy.weixin.token.TokenHandler;

/**
 * 
 * @author wy
 * @version v 0.1 2013-11-20 下午8:26:54 wy Exp $
 */
public abstract class AbstractRequestHandler implements RequestHandler {

    /** 日志 */
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private TokenHandler tokenHandler;

    /**
     * @see com.easy.weixin.filter.handler.RequestHandler#handleRequest(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response,
                              FilterChain filterChain) {
        try {
            RequestToken requestToken = tokenHandler.getRequestToken(request, response);
            log.debug("请求的数据如下:{}", requestToken);
            if (!requestToken.isValid()) {
                log.warn("验证服务器信息失败,请求的数据如下:{}", requestToken);
                ResponseUtil.writeText(response, "error");
                return;
            }
            if ("GET".equalsIgnoreCase(request.getMethod())) {
                ResponseUtil.writeText(response, requestToken.getEchostr());
            } else if ("POST".equalsIgnoreCase(request.getMethod())) {
                handleMessageRequest(request, response, filterChain, requestToken);
            } else {
                log.warn("未知的请求[{}]方式", request.getMethod());
                ResponseUtil.writeText(response, "error");
            }
        }
        catch (Exception e) {
            log.error("处理微信请求过程中出现异常", e);
            ResponseUtil.writeText(response, "error");
        }
        
    }

    /**
     * 处理消息
     * 
     * @param request
     * @param response
     * @param filterChain
     * @param requestToken
     */
    public abstract void handleMessageRequest(HttpServletRequest request,
                                              HttpServletResponse response,
                                              FilterChain filterChain, RequestToken requestToken);

}
