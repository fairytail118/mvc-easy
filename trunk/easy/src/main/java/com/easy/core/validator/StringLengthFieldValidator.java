/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

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

		ValidatorResult failResult = new ValidatorResult(validator.field(),
				values, validator.key(), validator.message());

		if (StringUtils.isNotBlank(validator.minLength())) {
			failResult.putValidParam("minLength", validator.minLength());
		}
		if (StringUtils.isNotBlank(validator.maxLength())) {
			failResult.putValidParam("maxLength", validator.maxLength());
		}

		// 为空，该字段没提交？
		if (values.length == 0) {
			return failResult;
		}

		int min = 0;
		int max = Integer.MAX_VALUE;
		if (StringUtils.isNotBlank(validator.maxLength())) {
			max = Integer.valueOf(validator.maxLength());
		}
		if (StringUtils.isNotBlank(validator.minLength())) {
			min = Integer.valueOf(validator.minLength());
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
