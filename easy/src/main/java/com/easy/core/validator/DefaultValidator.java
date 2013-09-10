/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;

import com.easy.core.validator.annotations.Validations;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-10 下午10:58:30 wy Exp $
 */
public class DefaultValidator implements Validator{

	/** 
	 * @see com.easy.core.validator.Validator#valid(com.easy.core.validator.annotations.Validations, org.springframework.validation.Errors, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void valid(Validations validations, Errors errors,
			HttpServletRequest request) {
		
		validations.emailValidators();
		
	}

}
