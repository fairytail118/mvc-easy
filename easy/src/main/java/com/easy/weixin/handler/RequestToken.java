/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.weixin.handler;

import java.io.Serializable;

/**
 * 请求的token信息
 * 
 * http://mp.weixin.qq.com/wiki/index.php?title=%E9%AA%8C%E8%AF%81%E6%B6%88%E6%
 * 81%AF%E7%9C%9F%E5%AE%9E%E6%80%A7
 * 
 * @author wy
 * @version v 0.1 2013-11-20 下午12:46:18 wy Exp $
 */
public class RequestToken implements Serializable {

    /**  */
    private static final long serialVersionUID = -4571879317619394518L;

    /** token */
    private String token;

    /** 获取的映射token */
    private String tokenMapping;

    /** 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数 */
    private String signature;

    /** 时间戳 */
    private String timestamp;

    /** 随机数 */
    private String nonce;

    /** 随机字符串 */
    private String echostr;

    /** 检查是否有效 */
    private boolean isValid = false;

    /**
     * Getter method for property <tt>token</tt>.
     * 
     * @return property value of token
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter method for property <tt>token</tt>.
     * 
     * @param token value to be assigned to property token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Getter method for property <tt>tokenMapping</tt>.
     * 
     * @return property value of tokenMapping
     */
    public String getTokenMapping() {
        return tokenMapping;
    }

    /**
     * Setter method for property <tt>tokenMapping</tt>.
     * 
     * @param tokenMapping value to be assigned to property tokenMapping
     */
    public void setTokenMapping(String tokenMapping) {
        this.tokenMapping = tokenMapping;
    }

    /**
     * Getter method for property <tt>signature</tt>.
     * 
     * @return property value of signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Setter method for property <tt>signature</tt>.
     * 
     * @param signature value to be assigned to property signature
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * Getter method for property <tt>timestamp</tt>.
     * 
     * @return property value of timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Setter method for property <tt>timestamp</tt>.
     * 
     * @param timestamp value to be assigned to property timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Getter method for property <tt>nonce</tt>.
     * 
     * @return property value of nonce
     */
    public String getNonce() {
        return nonce;
    }

    /**
     * Setter method for property <tt>nonce</tt>.
     * 
     * @param nonce value to be assigned to property nonce
     */
    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    /**
     * Getter method for property <tt>echostr</tt>.
     * 
     * @return property value of echostr
     */
    public String getEchostr() {
        return echostr;
    }

    /**
     * Setter method for property <tt>echostr</tt>.
     * 
     * @param echostr value to be assigned to property echostr
     */
    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }

}
