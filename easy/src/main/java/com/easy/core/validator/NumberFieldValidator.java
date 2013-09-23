/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator;

import javax.servlet.http.HttpServletRequest;

import com.easy.core.validator.annotations.NumberValidator;
import com.easy.core.validator.utils.RegexUtil;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-11 下午7:19:25 wy Exp $
 */
public class NumberFieldValidator extends AbstractFieldValidator<NumberValidator> {

    /**
     * @see com.easy.core.validator.FieldValidator#isValid(java.lang.Object,
     *      javax.servlet.http.HttpServletRequest)
     */
    @Override
    public ValidatorResult isValid(NumberValidator validator, HttpServletRequest request) {

        String[] numbers = getParameters(validator.field(), request);

        ValidatorResult failResult = new ValidatorResult(validator.field(), numbers,
            validator.key(), validator.message());

        // 为空，该字段没提交？
        if (numbers.length == 0) {
            return failResult;
        }

        for (String num : numbers) {
            // 整数判断
            if (!validator.decimals()) {
                if (!RegexUtil.valid("^([-]{0,1})[1-9]\\d+$", num)) {
                    return failResult;
                }

            } else if (!RegexUtil.valid("^([-]{0,1})[1-9]\\d+(\\.)?\\d+$", num)) {
                return failResult;
            }
        }

        return new ValidatorResult(numbers);
    }

}
