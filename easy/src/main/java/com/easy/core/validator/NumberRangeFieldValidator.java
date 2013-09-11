/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator;

import javax.servlet.http.HttpServletRequest;

import com.easy.core.validator.annotations.NumberRangeValidator;
import com.easy.core.validator.util.RegexUtil;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-11 下午7:19:25 wy Exp $
 */
public class NumberRangeFieldValidator extends
		AbstractFieldValidator<NumberRangeValidator> {

	/**
	 * @see com.easy.core.validator.FieldValidator#isValid(java.lang.Object,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ValidatorResult isValid(NumberRangeValidator validator,
			HttpServletRequest request) {

		String[] numbers = getParameters(validator.field(), request);

		ValidatorResult failResult = new ValidatorResult(numbers,
				validator.key(), validator.message()).putValidParam("min",
				validator.min()).putValidParam("max", validator.max());

		// 为空，该字段没提交？
		if (numbers.length == 0) {
			return failResult;
		}

		for (String num : numbers) {
			// 整数判断
			if (!validator.decimals()) {
				if (!RegexUtil.valid("([-]{0,1})[1-9]\\d+", num)) {
					return failResult;
				}
				// 范围判断
				long value = Long.valueOf(num);
				if (validator.min().length() > 0
						&& value < Long.valueOf(validator.min())) {
					return failResult;
				}
				if (validator.max().length() > 0
						&& value > Long.valueOf(validator.max())) {
					return failResult;
				}

			} else {
				if (!RegexUtil.valid("([-]{0,1})[1-9]\\d+(\\.)?\\d+", num)) {
					return failResult;
				}
				// 范围判断
				double value = Double.valueOf(num);
				if (validator.min().length() > 0
						&& value < Double.valueOf(validator.min())) {
					return failResult;
				}
				if (validator.max().length() > 0
						&& value > Double.valueOf(validator.max())) {
					return failResult;
				}
			}
		}

		return new ValidatorResult(numbers);
	}

}
