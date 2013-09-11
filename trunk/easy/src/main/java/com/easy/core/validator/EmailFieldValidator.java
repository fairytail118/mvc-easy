/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator;

import javax.servlet.http.HttpServletRequest;

import com.easy.core.validator.annotations.EmailValidator;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-10 下午11:05:08 wy Exp $
 */
public class EmailFieldValidator extends AbstractFieldValidator<EmailValidator> {

	/**
	 * @see com.easy.core.validator.FieldValidator#isValid(java.lang.Object,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ValidatorResult isValid(EmailValidator validator, HttpServletRequest request) {

		String[] emails = getParameters(validator.field(), request);
		
		if(emails.length==0){
			return null;
		}
		
		for(String email:emails){
			
		}

		return null;
	}

}
