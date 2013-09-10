/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.exceptions;

/**
 * 
 * @author wy
 * @version v 0.1 2013-6-16 下午9:42:06 wy Exp $
 */
public class EasyException extends RuntimeException {

    /** serialVersionUID */
    private static final long serialVersionUID = 6379406452520880427L;

    /**
     * @param message
     * @param cause
     */
    public EasyException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public EasyException(String message) {
        super(message);
    }

}
