/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;

import com.easy.core.validator.annotations.Validations;

/**
 * 验证
 * 
 * @author wy
 * @version v 0.1 2013-9-10 下午10:51:01 wy Exp $
 */
public interface Validator {
	
	/**
	 * 验证数据
	 * 
	 * @param validations
	 * @param errors
	 * @param request
	 */
	public void valid(Validations validations,Errors errors,HttpServletRequest request);

}