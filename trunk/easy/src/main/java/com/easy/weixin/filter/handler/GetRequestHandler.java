/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.weixin.filter.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.DigestUtils;

/**
 * 
 * 
 * @author wy
 * @version v 0.1 2013-11-19 下午10:18:48 wy Exp $
 */
public class GetRequestHandler implements RequestHandler {

    /**
     * @see com.easy.weixin.filter.handler.RequestHandler#handler(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void handler(HttpServletRequest request, HttpServletResponse response) {

        // 微信加密签名
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        //得到token的映射信息
        String path = request.getServletPath().substring(0,
            request.getServletPath().lastIndexOf("/"));
        String tokenMapping = path.substring(path.lastIndexOf("/"));
        String token = TokenMapTools.getToken(tokenMapping);

        //将token、timestamp、nonce三个参数进行字典序排序
        List<String> list = new ArrayList<String>(3);
        list.add(token);
        list.add(timestamp);
        list.add(nonce);
        Collections.sort(list);

        if (DigestUtils.shaHex(list.get(0) + list.get(1) + list.get(2)).equalsIgnoreCase(signature)) {
            try {
                response.setContentType("application/html;charset=UTF-8");
                //不缓存
                response.setHeader("Pragma", "No-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expires", 0);
                response.getWriter().write(echostr);
            }
            catch (IOException e) {
                response.getWriter().write("error");
            }
            catch (Exception e) {
                response.getWriter().write("error");
            }
        }
    }

    /**
     * @see com.easy.weixin.filter.handler.RequestHandler#supportRequest(java.lang.String)
     */
    @Override
    public boolean supportRequest(String method) {
        return "GET".equalsIgnoreCase(method);
    }

}
