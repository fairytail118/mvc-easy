/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.security.exception;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * 验证码错误异常
 * 
 * @author wy
 * @version v 0.1 2013-9-26 下午10:49:22 wy Exp $
 */
public class CodeAuthenticationServiceException extends AuthenticationServiceException {

    /** serialVersionUID */
    private static final long serialVersionUID = -4464896477076078821L;

    /**
     * @param msg
     */
    public CodeAuthenticationServiceException(String msg) {
        super(msg);
    }

}
