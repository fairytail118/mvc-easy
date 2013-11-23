/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.weixin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token处理
 * 
 * @author wy
 * @version v 0.1 2013-11-20 下午12:31:17 wy Exp $
 */
public interface TokenHandler {

    /**
     * 获取token信息
     * 
     * @param request
     * @param response
     * @return
     */
    public RequestToken getToken(HttpServletRequest request, HttpServletResponse response);

}
