/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator;

import javax.servlet.http.HttpServletRequest;

import com.easy.core.validator.annotations.StringLengthValidator;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-10 下午11:05:08 wy Exp $
 */
public class StringLengthFieldValidator extends
		AbstractFieldValidator<StringLengthValidator> {

	/**
	 * @see com.easy.core.validator.FieldValidator#isValid(java.lang.Object,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ValidatorResult isValid(StringLengthValidator validator,
			HttpServletRequest request) {

		String[] values = getParameters(validator.field(), request);

		ValidatorResult failResult = new ValidatorResult(values,
				validator.key(), validator.message());

		// 为空，该字段没提交？
		if (values.length == 0) {
			return failResult;
		}

		int min = 0;
		int max = validator.maxLength();
		if (validator.minLength() > 0) {
			min = validator.minLength();
		}
		if (max < min && max <= 0) {
			throw new ValidatorException("字符串最大长度" + max + "小于"
					+ (min < 0 ? 0 : min));
		}

		for (String value : values) {
			if (value == null
					|| (validator.trim() && (value.trim().length() < min || value
							.trim().length() > max))
					|| (!validator.trim() && (value.length() < min || value
							.length() > max))) {
				return failResult;
			}
		}

		return new ValidatorResult(values);
	}
}
