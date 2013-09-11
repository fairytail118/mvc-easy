/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator;

/**
 * 验证的异常
 * 
 * @author wy
 * @version v 0.1 2013-9-11 下午7:41:56 wy Exp $
 */
public class ValidatorException extends RuntimeException {

	/**  */
	private static final long serialVersionUID = -2682742310409783894L;

	/**
	 * @param message
	 */
	public ValidatorException(String message) {
		super(message);
	}

}
