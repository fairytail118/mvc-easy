/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.codec.Hex;

/**
 * 密码加密策略
 * 
 * @author wy
 * @version v 0.1 2013-9-24 下午11:04:28 wy Exp $
 */
public class EasyPasswordEncoder implements PasswordEncoder {

    /** 日志 */
    private final static Logger log = LoggerFactory.getLogger(EasyPasswordEncoder.class);

    /**
     * 加密
     * 
     * @param rawPass
     *            传入密码
     * @param salt
     *            加密混淆器
     * @return
     * 
     * @see org.springframework.security.authentication.encoding.PasswordEncoder#encodePassword(java.lang.String,
     *      java.lang.Object)
     */
    @Override
    public String encodePassword(String rawPass, Object salt) throws DataAccessException {
        try {

            //新的密码
            String saltedPass = rawPass + String.valueOf(salt);

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] digest = null;

            try {
                digest = messageDigest.digest(saltedPass.getBytes("UTF-8"));
            }
            catch (UnsupportedEncodingException e) {
                throw new IllegalStateException("UTF-8 not supported!");
            }
            return new String(Hex.encode(digest));
        }
        catch (Exception e) {
            log.error("加密密码出错", e);
        }
        return "";
    }

    /**
     * 验证密码是否匹配
     * 
     * @see org.springframework.security.authentication.encoding.PasswordEncoder#isPasswordValid(java.lang.String,
     *      java.lang.String, java.lang.Object)
     */
    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt)
                                                                               throws DataAccessException {
        return encPass.equalsIgnoreCase(encodePassword(rawPass, salt));
    }

}
