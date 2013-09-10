/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-10 下午11:05:08 wy Exp $
 */
public class EmailFieldValidator implements FieldValidator<EmailFieldValidator>{

	/** 
	 * @see com.easy.core.validator.FieldValidator#isValid(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean isValid(EmailFieldValidator validator, String value) {
		return false;
	}

}
