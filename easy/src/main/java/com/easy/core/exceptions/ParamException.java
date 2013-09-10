/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.exceptions;

/**
 * 参数异常
 * 
 * @author wy
 * @version v 0.1 2013-6-16 下午10:16:05 wy Exp $
 */
public class ParamException extends EasyException {

    /** serialVersionUID */
    private static final long serialVersionUID = 4274100965094572200L;

    /**
     * @param message
     * @param cause
     */
    public ParamException(String message, Throwable cause) {
        super("参数异常" + message, cause);
    }

    /**
     * @param message
     */
    public ParamException(String message) {
        super("参数异常" + message);
    }

}
