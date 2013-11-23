/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.weixin.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.easy.weixin.exceptions.WeixinException;
import com.easy.weixin.utils.SHA1Util;

/**
 * 
 * @author wy
 * @version v 0.1 2013-11-20 下午12:55:44 wy Exp $
 */
@Component
public class EasyTokenHandler implements TokenHandler {

    /**
     * @see com.easy.weixin.handler.TokenHandler#getRequestToken(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public RequestToken getRequestToken(HttpServletRequest request, HttpServletResponse response) {

        RequestToken requestToken = new RequestToken();

        // 微信加密签名
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        //得到token的映射信息
        String path = request.getServletPath();
        String tokenMapping = getTokenMapping(path);

        requestToken.setEchostr(echostr);
        requestToken.setNonce(nonce);
        requestToken.setSignature(signature);
        requestToken.setTimestamp(timestamp);
        requestToken.setToken(token);
        requestToken.setTokenMapping(tokenMapping);

        check(requestToken);

        return requestToken;
    }

    /**
     * 根据访问路径得到tokenMapping的信息
     * 
     * @param path
     * @return
     */
    protected String getTokenMapping(String path) {

        if (StringUtils.isBlank(path) || path.indexOf("/") == -1) {
            throw new WeixinException("无法从[" + path + "]提取tokenMapping的信息");
        }
        return path.substring(path.lastIndexOf("/"));
    }

    /**
     * 检查token，验证是否合法
     * 
     * @param token
     * @return
     */
    public void check(RequestToken token) {

        //将token、timestamp、nonce三个参数进行字典序排序

        List<String> list = new ArrayList<String>(3);
        list.add(token.getToken());
        list.add(token.getTimestamp());
        list.add(token.getNonce());
        Collections.sort(list);
        //检查结果
        token.setValid(SHA1Util.encode(list.get(0) + list.get(1) + list.get(2)).equalsIgnoreCase(
            token.getSignature()));
    }

}
