/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator;

import javax.servlet.http.HttpServletRequest;

import com.easy.core.validator.annotations.RequiredValidator;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-10 下午11:05:08 wy Exp $
 */
public class RequiredFieldValidator extends
		AbstractFieldValidator<RequiredValidator> {

	/**
	 * @see com.easy.core.validator.FieldValidator#isValid(java.lang.Object,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ValidatorResult isValid(RequiredValidator validator,
			HttpServletRequest request) {

		String[] values = getParameters(validator.field(), request);

		ValidatorResult failResult = new ValidatorResult(validator.field(),
				values, validator.key(), validator.message());

		// 为空，该字段没提交？
		if (values.length == 0) {
			return failResult;
		}

		for (String value : values) {
			if (value == null) {
				return failResult;
			}
		}

		return new ValidatorResult(values);
	}

}
