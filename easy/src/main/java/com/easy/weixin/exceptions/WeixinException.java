/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.weixin.exceptions;

/**
 * 
 * @author wy
 * @version v 0.1 2013-11-20 下午7:56:42 wy Exp $
 */
public class WeixinException extends RuntimeException {

    /** serialVersionUID */
    private static final long serialVersionUID = -66313518664280233L;

    /**
     * @param message
     * @param cause
     */
    public WeixinException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public WeixinException(String message) {
        super(message);
    }

}
