/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.easy.core.validator.annotations.EqualsStringValidator;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-28 下午5:11:51 wy Exp $
 */
public class EqualsStringFieldValidator extends AbstractFieldValidator<EqualsStringValidator> {

    /**
     * @see com.easy.core.validator.FieldValidator#isValid(java.lang.Object,
     *      javax.servlet.http.HttpServletRequest)
     */
    @Override
    public ValidatorResult isValid(EqualsStringValidator validator, HttpServletRequest request) {
        String[] values = getParameters(validator.field(), request);
        String[] otherValues = getParameters(validator.otherField(), request);

        ValidatorResult failResult = new ValidatorResult(validator.field(), values,
            validator.key(), validator.message()).putValidParam("otherField",
            validator.otherField()).putValidParam("ignoreCase",
            String.valueOf(validator.ignoreCase()));

        //比较 肯定只能长度为1
        if (values.length != 1 || otherValues.length != 1) {
            log.error("比较的字符串获取的values长度不为1,{}.values.lenth={},{}.values.lenth={}", new Object[] {
                    validator.field(), values.length, validator.otherField(), otherValues.length });
            return failResult;
        }

        if (validator.trim()
            && validator.ignoreCase()
            && StringUtils.equalsIgnoreCase(StringUtils.trimToEmpty(values[0]),
                StringUtils.trimToEmpty(otherValues[0]))) {
            return new ValidatorResult(values);
        } else if (validator.trim()
                   && !validator.ignoreCase()
                   && StringUtils.equals(StringUtils.trimToEmpty(values[0]),
                       StringUtils.trimToEmpty(otherValues[0]))) {
            return new ValidatorResult(values);
        } else if (!validator.trim() && validator.ignoreCase()
                   && StringUtils.equalsIgnoreCase(values[0], otherValues[0])) {
            return new ValidatorResult(values);
        } else if (!validator.trim() && !validator.ignoreCase()
                   && StringUtils.equals(values[0], otherValues[0])) {
            return new ValidatorResult(values);
        }

        return failResult;
    }
}
