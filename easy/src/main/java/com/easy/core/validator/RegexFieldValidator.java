/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator;

import javax.servlet.http.HttpServletRequest;

import com.easy.core.validator.annotations.RegexValidator;
import com.easy.core.validator.utils.RegexUtil;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-10 下午11:05:08 wy Exp $
 */
public class RegexFieldValidator extends AbstractFieldValidator<RegexValidator> {

	/**
	 * @see com.easy.core.validator.FieldValidator#isValid(java.lang.Object,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ValidatorResult isValid(RegexValidator validator,
			HttpServletRequest request) {

		String[] values = getParameters(validator.field(), request);

		ValidatorResult failResult = new ValidatorResult(validator.field(),
				values, validator.key(), validator.message()).putValidParam(
				"regex", validator.regex());

		// 为空，该字段没提交？
		if (values.length == 0) {
			return failResult;
		}

		for (String value : values) {
			if (!RegexUtil.valid(validator.regex(), value)) {
				return failResult;
			}
		}

		return new ValidatorResult(values);
	}

}
